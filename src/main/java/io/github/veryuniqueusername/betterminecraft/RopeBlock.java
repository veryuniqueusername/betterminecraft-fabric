package io.github.veryuniqueusername.betterminecraft;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RopeBlock extends Block implements Waterloggable {

	//	PROPERTIES
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	public static final EnumProperty<RopeType> TYPE = EnumProperty.of("type", RopeType.class);
	protected static final VoxelShape BOUNDING_BOX = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

	public RopeBlock(FabricBlockSettings properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, Boolean.FALSE).with(TYPE, RopeType.MIDDLE));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED, TYPE);
	}

	public PistonBehavior getPistonBehavior(@NotNull BlockState state) {
		return PistonBehavior.DESTROY;
	}

	@Nullable
	public BlockState getPlacementState(ItemPlacementContext context) {
		BlockPos blockpos = context.getBlockPos();
		World world = context.getWorld();
		FluidState fluidstate = world.getFluidState(context.getBlockPos());
		boolean flag = fluidstate.getFluid() == Fluids.WATER;
		BlockState state = super.getPlacementState(context);
		return state == null ? null : state.with(WATERLOGGED, flag).with(TYPE, this.getType(world, blockpos));
	}

	public BlockState getStateForNeighborUpdate(BlockState stateIn, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.createAndScheduleFluidTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		if (!worldIn.isClient()) {
			worldIn.createAndScheduleBlockTick(currentPos, this, 1);
		}

		return super.getStateForNeighborUpdate(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (!worldIn.isClient) {
			worldIn.createAndScheduleBlockTick(pos, this, 1);
		}

	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		BlockState blockstate = state.with(TYPE, getType(world, pos));
		if (!canPlaceAt(world, pos)) {
			world.breakBlock(pos, true);
		}
		else if (state != blockstate) {
			world.setBlockState(pos, blockstate, 3);
		}
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
		return BOUNDING_BOX;
	}

	public RopeType getType(World world, BlockPos pos) {
		if (!world.getBlockState(pos.up()).isOf(this) && !world.getBlockState(pos.down()).isOf(this)) {
			return RopeType.BOTH;
		} else if (!world.getBlockState(pos.up()).isOf(this)) {
			return RopeType.TOP;
		} else if (!world.getBlockState(pos.down()).isOf(this)) {
			return RopeType.BOTTOM;
		} else {
			return RopeType.MIDDLE;
		}
	}

	public boolean canPlaceAt(ServerWorld world, BlockPos pos) {
		return world.getBlockState(pos.up()).getMaterial().isSolid();
	}

	public boolean canReplace(BlockState state, ItemPlacementContext useContext) {
		return useContext.getStack().isOf(this.asItem());
	}

	public boolean canPathfindThrough(BlockState state, BlockView worldIn, BlockPos pos, NavigationType type) {
		return false;
	}
}

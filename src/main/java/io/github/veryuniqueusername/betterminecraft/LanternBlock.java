package io.github.veryuniqueusername.betterminecraft;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class LanternBlock extends Block implements Waterloggable {
	public static final DirectionProperty FACING = Properties.FACING;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public static final VoxelShape[] SHAPES = new VoxelShape[]{
		VoxelShapes.union( //UP
			Block.createCuboidShape(5, 0, 5, 11, 7, 11),
			Block.createCuboidShape(6, 7, 6, 10, 9, 10)),
		VoxelShapes.union( //DOWN
			Block.createCuboidShape(5, 1, 5, 11, 8, 11),
			Block.createCuboidShape(6, 8, 6, 10, 10, 10)),
		VoxelShapes.union( //NORTH
			Block.createCuboidShape(5, 2, 5, 11, 9, 11),
			Block.createCuboidShape(6, 9, 6, 10, 11, 10),
			Block.createCuboidShape(7, 13, 6, 9, 14, 16)),
		VoxelShapes.union( //EAST
			Block.createCuboidShape(5, 2, 5, 11, 9, 11),
			Block.createCuboidShape(6, 9, 6, 10, 11, 10),
			Block.createCuboidShape(0, 13, 7, 10, 14, 9)),
		VoxelShapes.union( //SOUTH
			Block.createCuboidShape(5, 2, 5, 11, 9, 11),
			Block.createCuboidShape(6, 9, 6, 10, 11, 10),
			Block.createCuboidShape(7, 13, 0, 9, 14, 10)),
		VoxelShapes.union( //WEST
			Block.createCuboidShape(5, 2, 5, 11, 9, 11),
			Block.createCuboidShape(6, 9, 6, 10, 11, 10),
			Block.createCuboidShape(6, 13, 7, 16, 14, 9))};

	public LanternBlock(FabricBlockSettings properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(FACING, Direction.UP).with(WATERLOGGED, Boolean.FALSE));
	}

	@Nullable
	public BlockState getPlacementState(ItemPlacementContext context) {
		Direction direction = context.getSide();
		FluidState fluidstate = context.getWorld().getFluidState(context.getBlockPos());
		boolean flag = fluidstate.getFluid() == Fluids.WATER;
		BlockState state = super.getPlacementState(context);
		return state == null ? null : state.with(FACING, direction).with(WATERLOGGED, flag);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
		return switch (state.get(FACING)) {
			case UP -> SHAPES[0];
			case DOWN -> SHAPES[1];
			case NORTH -> SHAPES[2];
			case EAST -> SHAPES[3];
			case SOUTH -> SHAPES[4];
			case WEST -> SHAPES[5];
		};
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	public boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
		Direction direction = state.get(FACING);
		BlockPos blockpos = pos.offset(direction.getOpposite());
		return Block.sideCoversSmallSquare(world, blockpos, direction);
	}

	public PistonBehavior getPistonBehavior(BlockState state) {
		return PistonBehavior.DESTROY;
	}

	/**
	 * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
	 * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
	 * returns its solidified counterpart.
	 * Note that this method should ideally consider only the specific face passed in.
	 */

	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess world, BlockPos currentPos, BlockPos facingPos) {
		return this.canSurvive(state, world, currentPos) ? state : Blocks.AIR.getDefaultState();
	}

	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	public boolean canPathfindThrough(BlockState state, BlockView worldIn, BlockPos pos, NavigationType type) {
		return false;
	}
}
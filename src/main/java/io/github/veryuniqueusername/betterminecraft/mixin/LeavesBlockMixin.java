package io.github.veryuniqueusername.betterminecraft.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

import static io.github.veryuniqueusername.betterminecraft.BetterMinecraft.MOD_LOGGER;
import static io.github.veryuniqueusername.betterminecraft.BetterMinecraft.amount;
import static net.minecraft.block.LeavesBlock.DISTANCE;
import static net.minecraft.block.LeavesBlock.PERSISTENT;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin extends Block {
	private static final BooleanProperty EXPOSED = BooleanProperty.of("exposed");

	public LeavesBlockMixin(Settings properties) {
		super(properties);
		this.setDefaultState(this.stateManager.getDefaultState().with(DISTANCE, 7).with(PERSISTENT, false).with(EXPOSED, true));
	}

	@Override
	public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(DISTANCE, PERSISTENT, EXPOSED);
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		world.setBlockState(pos, updateDistanceFromLogs(state, world, pos), Block.NOTIFY_ALL);
		world.setBlockState(pos, getExposed(state, world, pos), Block.NOTIFY_ALL);
	}

	private static BlockState updateDistanceFromLogs(BlockState state, WorldAccess world, BlockPos pos) {
		int i = 7;
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		for (Direction direction: Direction.values()) {
			mutable.set(pos, direction);
			i = Math.min(i, getDistanceFromLog(world.getBlockState(mutable)) + 1);
			if (i == 1) break;
		}
		return state.with(DISTANCE, i);
	}

	private static int getDistanceFromLog(BlockState state) {
		if (state.isIn(BlockTags.LOGS)) {
			return 0;
		}
		if (state.getBlock() instanceof LeavesBlock) {
			return state.get(DISTANCE);
		}
		return 7;
	}

	private static BlockState getExposed(BlockState state, WorldAccess world, BlockPos pos) {
		amount += 1;
		MOD_LOGGER.info(amount);
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		for (Direction direction: Direction.values()) {
			mutable.set(pos, direction);
			if (!(world.getBlockState(mutable).isIn(BlockTags.LEAVES)))
				return state.with(EXPOSED, true);
		}
		return state.with(EXPOSED, false);
	}
}

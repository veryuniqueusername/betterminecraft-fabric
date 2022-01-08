package io.github.veryuniqueusername.betterminecraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RopeItem extends BlockItem {
	public RopeItem(Block block, Settings group) {
		super(block, group);
	}

	@Nullable
	public ItemPlacementContext getPlacementContext(ItemPlacementContext ctx) {
		BlockPos blockpos = ctx.getBlockPos();
		World level = ctx.getWorld();
		BlockState blockstate = level.getBlockState(blockpos);
		Block block = this.getBlock();

		if (!level.getBlockState(blockpos.up()).getMaterial().isSolid()) {
			return null;
		} else if (!blockstate.isOf(block)) {
			return ctx;
		} else {
			Direction direction = Direction.DOWN;

			BlockPos.Mutable blockpos$mutable = blockpos.mutableCopy().move(direction);
			while (true) {
				blockstate = level.getBlockState(blockpos$mutable);
				if (!blockstate.isOf(this.getBlock())) {
					if (blockstate.canReplace(ctx)) {
						return ItemPlacementContext.offset(ctx, blockpos$mutable, direction);
					}
					break;
				}

				blockpos$mutable.move(direction);
			}
			return null;
		}
	}

	protected boolean checkStatePlacement() {
		return false;
	}
}

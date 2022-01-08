package io.github.veryuniqueusername.betterminecraft;

import io.github.veryuniqueusername.betterminecraft.blocks.LanternBlock;
import io.github.veryuniqueusername.betterminecraft.blocks.RopeBlock;
import io.github.veryuniqueusername.betterminecraft.blocks.RopeItem;
import io.github.veryuniqueusername.betterminecraft.blocks.VerticalSlabBlock;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static io.github.veryuniqueusername.betterminecraft.BetterMinecraft.MOD_ID;

public class RegistryHandler {

	public static Identifier identify(String name) {
		return new Identifier(MOD_ID, name);
	}

	private static Block registerBlock(String name, Block block) {
		return Registry.register(Registry.BLOCK, identify(name), block);
	}

	private static void registerItem(Block block, ItemGroup group) {
		Registry.register(Registry.ITEM, Registry.BLOCK.getId(block), new BlockItem(block, new FabricItemSettings().group(group)));
	}

	private static void registerPlanks(String color, FabricBlockSettings properties, MapColor mapColor) {
		registerItem(registerBlock(color + "_planks", new Block(properties)), ItemGroup.BUILDING_BLOCKS);
		registerItem(registerBlock(color + "_plank_slab", new SlabBlock(properties)), ItemGroup.BUILDING_BLOCKS);
		registerItem(registerBlock(color + "_plank_vertical_slab", new VerticalSlabBlock(properties)), ItemGroup.BUILDING_BLOCKS);
		registerItem(registerBlock(color + "_plank_stairs", new StairsBlock(new Block(properties).getDefaultState(), properties) {
		}), ItemGroup.BUILDING_BLOCKS);
		registerItem(registerBlock(color + "_plank_fence", new FenceBlock(properties)), ItemGroup.REDSTONE);
		registerItem(registerBlock(color + "_plank_fence_gate", new FenceGateBlock(properties)), ItemGroup.REDSTONE);
		registerItem(registerBlock(color + "_plank_button", new WoodenButtonBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().strength(0.5F).sounds(BlockSoundGroup.WOOD)) {
		}), ItemGroup.REDSTONE);
		registerItem(registerBlock(color + "_plank_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.WOOD, mapColor).noCollision().strength(0.5F).sounds(BlockSoundGroup.WOOD)) {
		}), ItemGroup.REDSTONE);
	}

	private static void registerVerticalSlab(Block parent, String baseName) {
		registerItem(registerBlock(baseName + "_vertical_slab", new VerticalSlabBlock(FabricBlockSettings.copyOf(parent))), ItemGroup.BUILDING_BLOCKS);
	}

	private static final FabricBlockSettings WHITE = FabricBlockSettings.of(Material.WOOD, MapColor.WHITE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings ORANGE = FabricBlockSettings.of(Material.WOOD, MapColor.ORANGE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings MAGENTA = FabricBlockSettings.of(Material.WOOD, MapColor.MAGENTA).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings LIGHT_BLUE = FabricBlockSettings.of(Material.WOOD, MapColor.LIGHT_BLUE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings YELLOW = FabricBlockSettings.of(Material.WOOD, MapColor.YELLOW).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings LIME = FabricBlockSettings.of(Material.WOOD, MapColor.LIME).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings PINK = FabricBlockSettings.of(Material.WOOD, MapColor.PINK).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings GRAY = FabricBlockSettings.of(Material.WOOD, MapColor.GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings LIGHT_GRAY = FabricBlockSettings.of(Material.WOOD, MapColor.LIGHT_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings CYAN = FabricBlockSettings.of(Material.WOOD, MapColor.CYAN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings PURPLE = FabricBlockSettings.of(Material.WOOD, MapColor.PURPLE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings BLUE = FabricBlockSettings.of(Material.WOOD, MapColor.BLUE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings BROWN = FabricBlockSettings.of(Material.WOOD, MapColor.BROWN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings GREEN = FabricBlockSettings.of(Material.WOOD, MapColor.GREEN).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings RED = FabricBlockSettings.of(Material.WOOD, MapColor.RED).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);
	private static final FabricBlockSettings BLACK = FabricBlockSettings.of(Material.WOOD, MapColor.BLACK).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD);

	public static void init() {
		registerPlanks("white", WHITE, MapColor.WHITE);
		registerPlanks("orange", ORANGE, MapColor.ORANGE);
		registerPlanks("magenta", MAGENTA, MapColor.MAGENTA);
		registerPlanks("light_blue", LIGHT_BLUE, MapColor.LIGHT_BLUE);
		registerPlanks("yellow", YELLOW, MapColor.YELLOW);
		registerPlanks("lime", LIME, MapColor.LIME);
		registerPlanks("pink", PINK, MapColor.PINK);
		registerPlanks("gray", GRAY, MapColor.GRAY);
		registerPlanks("light_gray", LIGHT_GRAY, MapColor.LIGHT_GRAY);
		registerPlanks("cyan", CYAN, MapColor.CYAN);
		registerPlanks("purple", PURPLE, MapColor.PURPLE);
		registerPlanks("blue", BLUE, MapColor.BLUE);
		registerPlanks("brown", BROWN, MapColor.BROWN);
		registerPlanks("green", GREEN, MapColor.GREEN);
		registerPlanks("red", RED, MapColor.RED);
		registerPlanks("black", BLACK, MapColor.BLACK);

		final Block LANTERN = registerBlock("lantern", new io.github.veryuniqueusername.betterminecraft.blocks.LanternBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance(15).nonOpaque()));
		registerItem(LANTERN, ItemGroup.DECORATIONS);
		final Block SOUL_LANTERN = registerBlock("soul_lantern", new LanternBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance(15).nonOpaque()));
		registerItem(SOUL_LANTERN, ItemGroup.DECORATIONS);

		final Block ROPE = registerBlock("rope", new RopeBlock(FabricBlockSettings.of(Material.WOOL).strength(1.0F, 0.5F).sounds(BlockSoundGroup.WOOL).nonOpaque()));
		Registry.register(Registry.ITEM, identify("rope"), new RopeItem(ROPE, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ROPE, LANTERN, SOUL_LANTERN);

		registerVerticalSlab(Blocks.OAK_SLAB, "oak");
		registerVerticalSlab(Blocks.SPRUCE_SLAB, "spruce");
		registerVerticalSlab(Blocks.BIRCH_SLAB, "birch");
		registerVerticalSlab(Blocks.JUNGLE_SLAB, "jungle");
		registerVerticalSlab(Blocks.ACACIA_SLAB, "acacia");
		registerVerticalSlab(Blocks.DARK_OAK_SLAB, "dark_oak");

		registerVerticalSlab(Blocks.PRISMARINE_SLAB, "prismarine");
		registerVerticalSlab(Blocks.PRISMARINE_BRICK_SLAB, "prismarine_brick");
		registerVerticalSlab(Blocks.DARK_PRISMARINE_SLAB, "dark_prismarine");

		registerVerticalSlab(Blocks.STONE_SLAB, "stone");
		registerVerticalSlab(Blocks.SMOOTH_STONE_SLAB, "smooth_stone");
		registerVerticalSlab(Blocks.SANDSTONE_SLAB, "sandstone");
		registerVerticalSlab(Blocks.CUT_SANDSTONE_SLAB, "cut_sandstone");
		registerVerticalSlab(Blocks.PETRIFIED_OAK_SLAB, "petrified_oak");
		registerVerticalSlab(Blocks.COBBLESTONE_SLAB, "cobblestone");
		registerVerticalSlab(Blocks.BRICK_SLAB, "brick");
		registerVerticalSlab(Blocks.STONE_BRICK_SLAB, "stone_brick");
		registerVerticalSlab(Blocks.NETHER_BRICK_SLAB, "nether_brick");
		registerVerticalSlab(Blocks.QUARTZ_SLAB, "quartz");
		registerVerticalSlab(Blocks.RED_SANDSTONE_SLAB, "red_sandstone");
		registerVerticalSlab(Blocks.CUT_RED_SANDSTONE_SLAB, "cut_red_sandstone");

		registerVerticalSlab(Blocks.POLISHED_GRANITE_SLAB, "polished_granite");
		registerVerticalSlab(Blocks.SMOOTH_RED_SANDSTONE_SLAB, "smooth_red_sandstone");
		registerVerticalSlab(Blocks.MOSSY_STONE_BRICK_SLAB, "mossy_stone_brick");
		registerVerticalSlab(Blocks.POLISHED_DIORITE_SLAB, "polished_diorite");
		registerVerticalSlab(Blocks.MOSSY_COBBLESTONE_SLAB, "mossy_cobblestone");
		registerVerticalSlab(Blocks.END_STONE_BRICK_SLAB, "end_stone_brick");
		registerVerticalSlab(Blocks.SMOOTH_SANDSTONE_SLAB, "smooth_sandstone");
		registerVerticalSlab(Blocks.SMOOTH_QUARTZ_SLAB, "smooth_quartz");
		registerVerticalSlab(Blocks.GRANITE_SLAB, "granite");
		registerVerticalSlab(Blocks.ANDESITE_SLAB, "andesite");
		registerVerticalSlab(Blocks.RED_NETHER_BRICK_SLAB, "red_nether_brick");
		registerVerticalSlab(Blocks.POLISHED_ANDESITE_SLAB, "polished_andesite");
		registerVerticalSlab(Blocks.DIORITE_SLAB, "diorite");

		registerVerticalSlab(Blocks.CRIMSON_SLAB, "crimson");
		registerVerticalSlab(Blocks.WARPED_SLAB, "warped");

		registerVerticalSlab(Blocks.BLACKSTONE_SLAB, "blackstone");
		registerVerticalSlab(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, "polished_blackstone_brick");
		registerVerticalSlab(Blocks.POLISHED_BLACKSTONE_SLAB, "polished_blackstone");

		registerVerticalSlab(Blocks.OXIDIZED_CUT_COPPER_SLAB, "oxidized_cut_copper");
		registerVerticalSlab(Blocks.WEATHERED_CUT_COPPER_SLAB, "weathered_cut_copper");
		registerVerticalSlab(Blocks.EXPOSED_CUT_COPPER_SLAB, "exposed_cut_copper");
		registerVerticalSlab(Blocks.CUT_COPPER_SLAB, "cut_copper");
		registerVerticalSlab(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB, "waxed_oxidized_cut_copper");
		registerVerticalSlab(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB, "waxed_weathered_cut_copper");
		registerVerticalSlab(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB, "waxed_exposed_cut_copper");
		registerVerticalSlab(Blocks.WAXED_CUT_COPPER_SLAB, "waxed_cut_copper");

		registerVerticalSlab(Blocks.COBBLED_DEEPSLATE_SLAB, "cobbled_deepslate");
		registerVerticalSlab(Blocks.POLISHED_DEEPSLATE_SLAB, "polished_deepslate");
		registerVerticalSlab(Blocks.DEEPSLATE_TILE_SLAB, "deepslate_tile");
		registerVerticalSlab(Blocks.DEEPSLATE_BRICK_SLAB, "deepslate_brick");
	}
}

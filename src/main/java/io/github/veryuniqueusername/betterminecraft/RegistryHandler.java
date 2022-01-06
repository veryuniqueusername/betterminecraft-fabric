package io.github.veryuniqueusername.betterminecraft;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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

	private static Item registerItem(Block block, ItemGroup group) {
		return Registry.register(Registry.ITEM, Registry.BLOCK.getId(block), new BlockItem(block, new FabricItemSettings().group(group)));
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

		final Block LANTERN = registerBlock("lantern", new LanternBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance(15).nonOpaque()));
		registerItem(LANTERN, ItemGroup.DECORATIONS);
		final Block SOUL_LANTERN = registerBlock("soul_lantern", new LanternBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance(15).nonOpaque()));
		registerItem(SOUL_LANTERN, ItemGroup.DECORATIONS);

		final Block ROPE = registerBlock("rope", new RopeBlock(FabricBlockSettings.of(Material.WOOL).strength(1.0F, 0.5F).sounds(BlockSoundGroup.WOOL).nonOpaque()));
		Registry.register(Registry.ITEM, identify("rope"), new RopeItem(ROPE, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ROPE, LANTERN, SOUL_LANTERN);
	}
}

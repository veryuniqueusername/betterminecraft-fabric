package io.github.veryuniqueusername.betterminecraft;

import net.fabricmc.api.ModInitializer;

import static io.github.veryuniqueusername.betterminecraft.RegistryHandler.init;

public class BetterMinecraft implements ModInitializer {
	public static final String MOD_ID = "betterminecraft";


	@Override
	public void onInitialize() {
		init();
	}
}

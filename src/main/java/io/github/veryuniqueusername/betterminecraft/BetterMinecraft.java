package io.github.veryuniqueusername.betterminecraft;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.github.veryuniqueusername.betterminecraft.RegistryHandler.init;

public class BetterMinecraft implements ModInitializer {
	public static final String MOD_ID = "betterminecraft";

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		init();
	}
}

package es.superstrellaa.blockgen;

import net.fabricmc.api.ModInitializer;

public class BlockGen implements ModInitializer {
	public static final String MOD_ID = "blockgen";

	@Override
	public void onInitialize() {
		ConfigManager.init();
		BlockRegistry.loadAll();
	}
}
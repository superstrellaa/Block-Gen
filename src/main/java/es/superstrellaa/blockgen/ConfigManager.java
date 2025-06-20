package es.superstrellaa.blockgen;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class ConfigManager {
    public static File CONFIG_ROOT;
    public static File BLOCKS_DIR;
    public static File MODELS_DIR;
    public static File TEXTURES_DIR;

    public static void init() {
        CONFIG_ROOT = new File(FabricLoader.getInstance().getConfigDir().toFile(), "blockgen");
        BLOCKS_DIR = new File(CONFIG_ROOT, "blocks");
        MODELS_DIR = new File(CONFIG_ROOT, "models");
        TEXTURES_DIR = new File(CONFIG_ROOT, "textures");

        if (!BLOCKS_DIR.exists()) BLOCKS_DIR.mkdirs();
        if (!MODELS_DIR.exists()) MODELS_DIR.mkdirs();
        if (!TEXTURES_DIR.exists()) TEXTURES_DIR.mkdirs();
    }
}
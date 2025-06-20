package es.superstrellaa.blockgen;

import com.google.gson.*;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class BlockRegistry {
    private static final Gson GSON = new Gson();

    public static void loadAll() {
        File[] jsonFiles = ConfigManager.BLOCKS_DIR.listFiles((dir, name) -> name.endsWith(".json"));
        if (jsonFiles == null) return;

        for (File file : jsonFiles) {
            try (FileReader reader = new FileReader(file)) {
                JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
                JsonObject blocks = root.getAsJsonObject("blocks");

                for (Map.Entry<String, JsonElement> entry : blocks.entrySet()) {
                    JsonObject data = entry.getValue().getAsJsonObject();
                    registerBlock(data);
                }
            } catch (Exception e) {
                System.err.println("[BlockGen] Error loading " + file.getName());
                e.printStackTrace();
            }
        }
    }

    private static void registerBlock(JsonObject data) {
        String id = data.get("id").getAsString();
        float strength = data.has("strength") ? data.get("strength").getAsFloat() : 1.0f;
        String soundType = data.has("sound") ? data.get("sound").getAsString() : "STONE";

        BlockSoundGroup sound = switch (soundType.toUpperCase()) {
            case "WOOD" -> BlockSoundGroup.WOOD;
            case "GRASS" -> BlockSoundGroup.GRASS;
            default -> BlockSoundGroup.STONE;
        };

        Block.Settings settings = Block.Settings.create()
                .strength(strength)
                .sounds(sound)
                .mapColor(MapColor.STONE_GRAY);

        Block block = new Block(settings);
        Identifier blockId = new Identifier(BlockGen.MOD_ID, id);
        Registry.register(Registries.BLOCK, blockId, block);
        Registry.register(Registries.ITEM, blockId, new BlockItem(block, new Item.Settings()));

        System.out.println("[BlockGen] Registered block: " + id);
    }
}

package me.blackfur.pm.content;

import me.blackfur.pm.materials.Material;
import me.blackfur.pm.materials.Materials;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
    public static List<Block> ORE_BLOCKS = new ArrayList<>();
    public static List<BlockItem> ORE_BLOCK_ITEMS = new ArrayList<>();

    public static void register() {
        for (var material : Materials.MATERIALS) {
            // Register material ore
            registerOre(material);
        }
    }

    private static void registerOre(Material material) {
        var name = Material.idFromString(material.rawName() + " Ore");
        var deepslate_name = Material.idFromString("Deepslate " + material.rawName() + " Ore");
        var ore_block = Registry.register(Registries.BLOCK,
                Identifier.of("pm", name),
                new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)));
        var ore_item = Registry.register(Registries.ITEM,
                Identifier.of("pm", name),
                new BlockItem(ore_block, new Item.Settings()));

        var deepslate_ore_block = Registry.register(Registries.BLOCK,
                Identifier.of("pm", deepslate_name),
                new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)));
        var deepslate_ore_item = Registry.register(Registries.ITEM,
                Identifier.of("pm", deepslate_name),
                new BlockItem(deepslate_ore_block, new Item.Settings()));

        ORE_BLOCKS.add(ore_block);
        ORE_BLOCK_ITEMS.add(ore_item);
        ORE_BLOCKS.add(deepslate_ore_block);
        ORE_BLOCK_ITEMS.add(deepslate_ore_item);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.NATURAL_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ore_item.getDefaultStack());
            itemGroup.add(deepslate_ore_item.getDefaultStack());
        });
    }
}

package me.blackfur.pm.content;

import me.blackfur.pm.ProjectMachines;
import me.blackfur.pm.generated.Material;
import me.blackfur.pm.generated.Materials;
import me.blackfur.pm.generated.Multiblock;
import me.blackfur.pm.generated.Multiblocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class GeneratedContent {
    public static List<Block> ORE_BLOCKS = new ArrayList<>();
    public static List<BlockItem> ORE_BLOCK_ITEMS = new ArrayList<>();
    public static List<Item> RAW_ORE_ITEMS = new ArrayList<>();
    public static List<Block> MULTIBLOCK_BLOCKS = new ArrayList<>();
    public static List<Item> MULTIBLOCK_ITEMS = new ArrayList<>();

    public static void register() {
        for (var material : Materials.MATERIALS) {
            // Register material ore
            registerOre(material);
        }

        for (var multiblock : Multiblocks.MULTIBLOCKS) {
            GeneratedContent.registerController(multiblock);
        }
    }

    private static void registerOre(Material material) {
        var name = ProjectMachines.idFromString(material.rawName() + " Ore");
        var deepslate_name = ProjectMachines.idFromString("Deepslate " + material.rawName() + " Ore");
        var raw_name = ProjectMachines.idFromString("Raw " + material.rawName() + " Ore");
        var raw_block_name = ProjectMachines.idFromString("Raw " + material.rawName() + " Ore Block");

        var ore_block = Registry.register(Registries.BLOCK,
                Identifier.of("pm", name),
                new Block(AbstractBlock.Settings.copy(Blocks.GOLD_ORE)));
        var ore_item = Registry.register(Registries.ITEM,
                Identifier.of("pm", name),
                new BlockItem(ore_block, new Item.Settings()));

        var deepslate_ore_block = Registry.register(Registries.BLOCK,
                Identifier.of("pm", deepslate_name),
                new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_GOLD_ORE)));
        var deepslate_ore_item = Registry.register(Registries.ITEM,
                Identifier.of("pm", deepslate_name),
                new BlockItem(deepslate_ore_block, new Item.Settings()));

        var raw_ore_item = Registry.register(Registries.ITEM,
                Identifier.of("pm", raw_name),
                new Item(new Item.Settings()));
        var raw_ore_block = Registry.register(Registries.BLOCK,
                Identifier.of("pm", raw_block_name),
                new Block(AbstractBlock.Settings.copy(Blocks.RAW_GOLD_BLOCK)));
        var raw_ore_block_item = Registry.register(Registries.ITEM,
                        Identifier.of("pm", raw_block_name),
                        new BlockItem(raw_ore_block, new Item.Settings()));

        ORE_BLOCKS.add(ore_block);
        ORE_BLOCK_ITEMS.add(ore_item);
        ORE_BLOCKS.add(deepslate_ore_block);
        ORE_BLOCK_ITEMS.add(deepslate_ore_item);
        RAW_ORE_ITEMS.add(raw_ore_item);
        ORE_BLOCKS.add(raw_ore_block);
        ORE_BLOCK_ITEMS.add(raw_ore_block_item);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.NATURAL_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(ore_item);
            itemGroup.add(deepslate_ore_item);
            itemGroup.add(raw_ore_item);
            itemGroup.add(raw_ore_block_item);
        });
    }

    public static void registerController(Multiblock multiblock) {
        var id = Identifier.of("pm", ProjectMachines.idFromString(multiblock.name()));
        var controller = Registry.register(Registries.BLOCK, id, multiblock.block());
        var controller_item = Registry.register(Registries.ITEM, id, new BlockItem(controller, new Item.Settings()));
        var controller_entity = Registry.register(Registries.BLOCK_ENTITY_TYPE, id, BlockEntityType.Builder.create(multiblock.blockEntity(), controller).build());

        multiblock.typeSetter().accept(controller_entity);

        MULTIBLOCK_BLOCKS.add(controller);
        MULTIBLOCK_ITEMS.add(controller_item);

        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.MACHINE_ITEM_GROUP_KEY).register((itemGroup) -> {
            itemGroup.add(controller_item);
        });
    }
}

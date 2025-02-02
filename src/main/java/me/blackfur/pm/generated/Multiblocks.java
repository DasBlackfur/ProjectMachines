package me.blackfur.pm.multiblock;

import me.blackfur.pm.ProjectMachines;
import me.blackfur.pm.multiblock.waterwheel.WaterwheelController;
import me.blackfur.pm.multiblock.waterwheel.WaterwheelEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Multiblocks {
    public static List<Multiblock> MULTIBLOCKS;
    public static List<Block> MULTIBLOCK_BLOCKS = new ArrayList<>();
    public static List<Item> MULTIBLOCK_ITEMS = new ArrayList<>();

    static {
        MULTIBLOCKS = List.of(
                new Multiblock("Waterwheel", new WaterwheelController(), WaterwheelEntity::new, WaterwheelEntity::setType)
        );
    }

    public static void register() {
        for (var multiblock : MULTIBLOCKS) {
            registerController(
                    Identifier.of("pm", ProjectMachines.idFromString(multiblock.name())),
                    multiblock.block(),
                    multiblock.blockEntity(),
                    multiblock.typeSetter());
        }
    }

    public static void registerController(Identifier id, Block block, BlockEntityType.BlockEntityFactory<? extends BlockEntity> blockEntity, Consumer<BlockEntityType<?>> typeSetter) {
        var controller = Registry.register(Registries.BLOCK, id, block);
        var controller_item = Registry.register(Registries.ITEM, id, new BlockItem(controller, new Item.Settings()));
        var controller_entity = Registry.register(Registries.BLOCK_ENTITY_TYPE, id, BlockEntityType.Builder.create(blockEntity, controller).build());

        typeSetter.accept(controller_entity);

        MULTIBLOCK_BLOCKS.add(controller);
        MULTIBLOCK_ITEMS.add(controller_item);
    }

}

package me.blackfur.pm.content;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> NATURAL_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of("pm:natural_item_group"));
    public static final ItemGroup NATURAL_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> GeneratedContent.ORE_BLOCK_ITEMS.getFirst().getDefaultStack())
            .displayName(Text.translatable("itemGroup.pm.natural"))
            .build();

    public static final RegistryKey<ItemGroup> MACHINE_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of("pm:machine_item_group"));
    public static final ItemGroup MACHINE_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> GeneratedContent.MULTIBLOCK_ITEMS.getFirst().getDefaultStack())
            .displayName(Text.translatable("itemGroup.pm.machine"))
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, NATURAL_ITEM_GROUP_KEY, NATURAL_ITEM_GROUP);
        Registry.register(Registries.ITEM_GROUP, MACHINE_ITEM_GROUP_KEY, MACHINE_ITEM_GROUP);
    }
}

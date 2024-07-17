package me.blackfur.pm.content;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> NATURAL_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of("pm:natural_item_group"));
    public static final ItemGroup NATURAL_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Blocks.STONE))
            .displayName(Text.translatable("itemGroup.pm.natural"))
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, NATURAL_ITEM_GROUP_KEY, NATURAL_ITEM_GROUP);
    }
}

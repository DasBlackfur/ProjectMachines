package me.blackfur.pm.datagen.providers;

import me.blackfur.pm.content.GeneratedContent;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        var ironToolTag = getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL);
        var pickaxeTag = getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE);

        for (var block : GeneratedContent.ORE_BLOCKS) {
            ironToolTag.add(block);
            pickaxeTag.add(block);
        }

        for (var block : GeneratedContent.MULTIBLOCK_BLOCKS) {
            ironToolTag.add(block);
            pickaxeTag.add(block);
        }
    }
}

package me.blackfur.pm.datagen.providers;

import me.blackfur.pm.content.GeneratedContent;
import me.blackfur.pm.generated.Materials;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LootTableProvider extends FabricBlockLootTableProvider {
    public LootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for (var material : Materials.MATERIALS) {
            addDrop(material.getOreBlock(false), oreDrops(material.getOreBlock(false), material.getOreItem()));
            addDrop(material.getOreBlock(true), oreDrops(material.getOreBlock(true), material.getOreItem()));
            addDrop(material.getRawOreBlock());
        }

        for (var block : GeneratedContent.MULTIBLOCK_BLOCKS) {
            addDrop(block);
        }
    }
}

package me.blackfur.pm.datagen.providers;

import me.blackfur.pm.content.GeneratedContent;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Generate ore models
        for (var block : GeneratedContent.ORE_BLOCKS) {
            blockStateModelGenerator.registerSimpleCubeAll(block);
        }

        for (var multiblock : GeneratedContent.MULTIBLOCK_BLOCKS) {
            blockStateModelGenerator.registerCubeAllModelTexturePool(multiblock);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Generate raw ore models
        for (var item : GeneratedContent.RAW_ORE_ITEMS) {
            itemModelGenerator.register(item, Models.GENERATED);
        }
    }
}

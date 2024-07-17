package me.blackfur.pm.datagen.providers;

import me.blackfur.pm.content.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Generate ore models
        for (var block : ModBlocks.ORE_BLOCKS) {
            blockStateModelGenerator.registerSimpleCubeAll(block);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}

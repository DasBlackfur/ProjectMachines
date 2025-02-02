package me.blackfur.pm.datagen.providers;

import me.blackfur.pm.generated.Materials;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        for (var material : Materials.MATERIALS) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, material.getOreItem(), 9).input(material.getRawOreBlock())
                    .criterion(FabricRecipeProvider.hasItem(material.getRawOreBlock()), FabricRecipeProvider.conditionsFromItem(material.getRawOreBlock()))
                    .offerTo(exporter);
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, material.getRawOreBlock()).input(material.getOreItem(), 9)
                    .criterion(FabricRecipeProvider.hasItem(material.getOreItem()), FabricRecipeProvider.conditionsFromItem(material.getOreItem()))
                    .offerTo(exporter);
        }
    }
}

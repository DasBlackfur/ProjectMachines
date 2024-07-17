package me.blackfur.pm.datagen.providers;

import me.blackfur.pm.materials.Material;
import me.blackfur.pm.materials.Materials;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class TranslationProvider extends FabricLanguageProvider {
    public TranslationProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/pm/lang/en_us.template.json").orElseThrow();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }


        for (var material : Materials.MATERIALS) {
            // Ore translation
            String name = material.rawName() + " Ore";
            String key = "block.pm." + Material.idFromString(name);
            translationBuilder.add(key, name);

            // Deepslate Ore translation
            name = "Deepslate " + material.rawName() + " Ore";
            key = "block.pm." + Material.idFromString(name);
            translationBuilder.add(key, name);
        }
    }
}

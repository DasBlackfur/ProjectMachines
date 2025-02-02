package me.blackfur.pm.datagen.providers;

import me.blackfur.pm.ProjectMachines;
import me.blackfur.pm.generated.Materials;
import me.blackfur.pm.generated.Multiblocks;
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
            String key = "block.pm." + ProjectMachines.idFromString(name);
            translationBuilder.add(key, name);

            // Deepslate Ore translation
            name = "Deepslate " + material.rawName() + " Ore";
            key = "block.pm." + ProjectMachines.idFromString(name);
            translationBuilder.add(key, name);

            // Raw Ore translation
            name = "Raw " + material.rawName() + " Ore";
            key = "item.pm." + ProjectMachines.idFromString(name);
            translationBuilder.add(key, name);

            name = "Block of Raw " + material.rawName();
            String keyName = "Raw " + material.rawName() + " Ore Block";
            key = "block.pm." + ProjectMachines.idFromString(keyName);
            translationBuilder.add(key, name);
        }

        for (var multiblock : Multiblocks.MULTIBLOCKS) {
            // Controller translation
            String name = multiblock.name() + " Controller";
            String keyName = multiblock.name();
            String key = "block.pm." + ProjectMachines.idFromString(keyName);
            translationBuilder.add(key, name);
        }
    }
}

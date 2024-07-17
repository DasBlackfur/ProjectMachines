package me.blackfur.pm.datagen;

import me.blackfur.pm.datagen.providers.ConfiguredFeatureProvider;
import me.blackfur.pm.datagen.providers.ModelProvider;
import me.blackfur.pm.datagen.providers.PlacedFeatureProvider;
import me.blackfur.pm.datagen.providers.TranslationProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ProjectMachinesDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(ModelProvider::new);
        pack.addProvider(TranslationProvider::new);
        pack.addProvider(ConfiguredFeatureProvider::new);
        pack.addProvider(PlacedFeatureProvider::new);
    }

    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureProvider::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PlacedFeatureProvider::bootstrap);
    }
}

package me.blackfur.pm.content;

import me.blackfur.pm.ProjectMachines;
import me.blackfur.pm.generated.Materials;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;

public class ModWorldGeneration {
    public static void register() {
        for (var material : Materials.MATERIALS) {
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                    RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of("pm", ProjectMachines.idFromString(material.rawName() + " Ore Placed"))));
        }
    }
}

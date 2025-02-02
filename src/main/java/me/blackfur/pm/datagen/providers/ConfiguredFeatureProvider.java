package me.blackfur.pm.datagen.providers;

import me.blackfur.pm.ProjectMachines;
import me.blackfur.pm.generated.Materials;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ConfiguredFeatureProvider extends FabricDynamicRegistryProvider {
    public ConfiguredFeatureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        var stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        var deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        for (var material : Materials.MATERIALS) {
            var targetRules = List.of(
                    OreFeatureConfig.createTarget(stoneReplacables, material.getOreBlock(false).getDefaultState()),
                    OreFeatureConfig.createTarget(deepslateReplacables, material.getOreBlock(true).getDefaultState())
            );

            context.register(
                    RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of("pm", ProjectMachines.idFromString(material.rawName() + " Ore"))),
                    new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(targetRules, material.oreMaterial().veinSize()))
            );
        }
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE));
    }

    @Override
    public String getName() {
        return "Configured features";
    }
}

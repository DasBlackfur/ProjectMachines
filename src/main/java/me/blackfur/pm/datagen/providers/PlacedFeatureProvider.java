package me.blackfur.pm.datagen.providers;

import me.blackfur.pm.ProjectMachines;
import me.blackfur.pm.generated.Materials;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PlacedFeatureProvider extends FabricDynamicRegistryProvider {
    public PlacedFeatureProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var registryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        for (var material : Materials.MATERIALS) {
            var oreMaterial = material.oreMaterial();
            context.register(
                    RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of("pm", ProjectMachines.idFromString(material.rawName() + " Ore Placed"))),
                    new PlacedFeature(registryLookup.getOrThrow(RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of("pm", ProjectMachines.idFromString(material.rawName() + " Ore")))),
                            List.of(
                                    CountPlacementModifier.of(oreMaterial.veinCount()),
                                    SquarePlacementModifier.of(),
                                    oreMaterial.biasedPlacement() ?
                                            HeightRangePlacementModifier.trapezoid(YOffset.fixed(oreMaterial.placementBottom()), YOffset.fixed(oreMaterial.placementTop())) :
                                            HeightRangePlacementModifier.uniform(YOffset.fixed(oreMaterial.placementBottom()), YOffset.fixed(oreMaterial.placementTop())),
                                    BiomePlacementModifier.of()
                            ))
            );
        }
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE));
    }

    @Override
    public String getName() {
        return "Placed Features";
    }
}

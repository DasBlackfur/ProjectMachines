package me.blackfur.pm.materials;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public record Material(String rawName, String processedName, OreMaterial oreMaterial) {
    public Material(String name, OreMaterial oreMaterial) {
        this(name, name, oreMaterial);
    }

    public static String idFromString(String string) {
        return string.toLowerCase().replace(" ", "_");
    }

    public Block getOreBlock(boolean deepslate) {
        return Registries.BLOCK.get(Identifier.of("pm", idFromString((deepslate ? "Deepslate " : "") + this.rawName() + " Ore")));
    }
}

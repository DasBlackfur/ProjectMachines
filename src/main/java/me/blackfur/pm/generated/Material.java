package me.blackfur.pm.generated;

import me.blackfur.pm.ProjectMachines;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public record Material(String rawName, String processedName, OreMaterial oreMaterial) {
    public Material(String name, OreMaterial oreMaterial) {
        this(name, name, oreMaterial);
    }

    public Block getOreBlock(boolean deepslate) {
        return Registries.BLOCK.get(Identifier.of("pm", ProjectMachines.idFromString((deepslate ? "Deepslate " : "") + this.rawName() + " Ore")));
    }

    public Item getOreItem() {
        return Registries.ITEM.get(Identifier.of("pm", ProjectMachines.idFromString("Raw " + this.rawName() + " Ore")));
    }

    public Block getRawOreBlock() {
        return Registries.BLOCK.get(Identifier.of("pm", ProjectMachines.idFromString("Raw " + this.rawName() + " Ore Block")));
    }
}

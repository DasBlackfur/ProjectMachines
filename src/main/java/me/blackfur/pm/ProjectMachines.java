package me.blackfur.pm;

import me.blackfur.pm.content.ModBlocks;
import me.blackfur.pm.content.ModItemGroups;
import me.blackfur.pm.content.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

public class ProjectMachines implements ModInitializer {

    @Override
    public void onInitialize() {
        ModItemGroups.register();
        ModBlocks.register();
        ModWorldGeneration.register();
    }
}

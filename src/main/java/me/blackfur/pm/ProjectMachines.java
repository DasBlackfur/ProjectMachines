package me.blackfur.pm;

import me.blackfur.pm.content.GeneratedContent;
import me.blackfur.pm.content.ModItemGroups;
import me.blackfur.pm.content.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

public class ProjectMachines implements ModInitializer {

    @Override
    public void onInitialize() {
        ModItemGroups.register();
        GeneratedContent.register();
        ModWorldGeneration.register();
    }

    public static String idFromString(String string) {
        return string.toLowerCase().replace(" ", "_");
    }
}

package me.blackfur.pm.generated;

import me.blackfur.pm.content.multiblock.waterwheel.WaterwheelController;
import me.blackfur.pm.content.multiblock.waterwheel.WaterwheelEntity;

import java.util.List;

public class Multiblocks {
    public static List<Multiblock> MULTIBLOCKS;

    static {
        MULTIBLOCKS = List.of(
                new Multiblock("Waterwheel", new WaterwheelController(), WaterwheelEntity::new, WaterwheelEntity::setType)
        );
    }
}

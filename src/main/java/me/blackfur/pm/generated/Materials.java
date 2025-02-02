package me.blackfur.pm.generated;

import java.util.List;

public class Materials {
    public static final List<Material> MATERIALS;

    static {
        MATERIALS = List.of(
                new Material("Bauxite", "Aluminium", new OreMaterial(
                        12, false, 30, -64, 192
                )),
                new Material("Rare Earth", new OreMaterial(
                        4, true, 10, -128, 64
                )),
                new Material("Lithium", new OreMaterial(
                        8, false, 20, -16, 128
                )),
                new Material("Uranium", new OreMaterial(
                        8, true, 15, -128, 16
                ))
        );
    }
}

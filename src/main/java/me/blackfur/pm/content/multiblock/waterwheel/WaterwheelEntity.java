package me.blackfur.pm.content.multiblock.waterwheel;

import me.blackfur.pm.content.multiblock.AbstractMultiblockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class WaterwheelEntity extends AbstractMultiblockEntity {
    public static BlockEntityType<?> BLOCK_ENTITY_TYPE;

    public WaterwheelEntity(BlockPos pos, BlockState state) {
        super(BLOCK_ENTITY_TYPE, pos, state);
    }

    public static void setType(BlockEntityType<?> type) {
        BLOCK_ENTITY_TYPE = type;
    }
}

package me.blackfur.pm.generated;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;

import java.util.function.Consumer;

public record Multiblock(String name, Block block, BlockEntityType.BlockEntityFactory<? extends BlockEntity> blockEntity, Consumer<BlockEntityType<?>> typeSetter) {
}

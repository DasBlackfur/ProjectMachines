package me.blackfur.pm.multiblock;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;

import java.util.function.Consumer;

public record Multiblock(String name, Block block, BlockEntityType.BlockEntityFactory<? extends BlockEntity> blockEntity, Consumer<BlockEntityType<?>> typeSetter) {
}

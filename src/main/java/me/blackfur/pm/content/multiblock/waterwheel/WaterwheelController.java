package me.blackfur.pm.multiblock.waterwheel;

import me.blackfur.pm.multiblock.AbstractMultiblockController;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WaterwheelController extends AbstractMultiblockController {
    public WaterwheelController() {
        super(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK));
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WaterwheelEntity(pos, state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof WaterwheelEntity waterwheelEntity) {
                waterwheelEntity.active = !waterwheelEntity.active;
                player.sendMessage(Text.literal("State: " + waterwheelEntity.active));

                return ActionResult.SUCCESS;
            }

        }
        return ActionResult.SUCCESS;
    }
}

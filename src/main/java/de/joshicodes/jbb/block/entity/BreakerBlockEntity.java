package de.joshicodes.jbb.block.entity;

import de.joshicodes.jbb.block.BreakerBlock;
import de.joshicodes.jbb.init.BlockEntityInit;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.BlockDestructionProgress;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ScheduledTick;
import net.minecraft.world.ticks.TickPriority;

import static de.joshicodes.jbb.block.BreakerBlock.POWERED;

public class BreakerBlockEntity extends BlockEntity {

    private int tick;
    private int breakProgress;
    private Block frontBlock;

    public BreakerBlockEntity(BlockEntityType<?> entity, BlockPos p_155229_, BlockState p_155230_) {
        super(entity, p_155229_, p_155230_);
    }

    public void tick(Level level, BreakerBlock.BreakerType type) {

        if(level == null) return;

        if(getBlockState().getValue(POWERED)) {
            level.setBlock(getBlockPos(), getBlockState().setValue(POWERED, false), 2);
            // update block behind
            level.neighborChanged(getBlockPos().relative(getBlockState().getValue(BreakerBlock.FACING).getOpposite()), getBlockState().getBlock(), getBlockPos());
        }

        BlockPos frontPos = getBlockPos().relative(getBlockState().getValue(BreakerBlock.FACING), 1);

        BlockState frontState = level.getBlockState(frontPos);

        if(frontState.isAir()) {
            breakProgress = 0;
            return;
        }

        Block frontBlock = frontState.getBlock();

        if(frontState.getDestroySpeed(level, frontPos) < 0) {
            breakProgress = 0;
            return;
        }

        if(!type.canHarvest(frontState)) {
            breakProgress = 0;
            return;  // Not the right tool / tier
        }

        if(tick < ((type.getSpeedModifier() * 2) + frontState.getDestroySpeed(level, frontPos))) {
            tick++;
            return;
        } else {
            tick = 0;
        }

        if(this.frontBlock == null || this.frontBlock != frontBlock) {
            this.frontBlock = frontBlock;
            breakProgress = 0;
        }

        breakProgress++;
        level.destroyBlockProgress(getBlockPos().hashCode(), frontPos, breakProgress);

        if(breakProgress >= 10) {
            level.destroyBlockProgress(getBlockPos().hashCode(), frontPos, -1);
            level.destroyBlock(frontPos, true);
            breakProgress = 0;
            BlockPos selfPos = getBlockPos();
            level.setBlock(selfPos, getBlockState().setValue(POWERED, true), 2);
            // update block behind
            level.neighborChanged(getBlockPos().relative(getBlockState().getValue(BreakerBlock.FACING).getOpposite()), getBlockState().getBlock(), getBlockPos());
        }

    }

}

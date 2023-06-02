package de.joshicodes.jbb.block.entity;

import de.joshicodes.jbb.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class StoneBreakerBlockEntity extends BreakerBlockEntity {

    public StoneBreakerBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityInit.STONE_BLOCK_BREAKER.get(), p_155229_, p_155230_);
    }

}

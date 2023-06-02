package de.joshicodes.jbb.block.entity;

import de.joshicodes.jbb.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class NetheriteBreakerBlockEntity extends BreakerBlockEntity {

    public NetheriteBreakerBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityInit.NETHERITE_BLOCK_BREAKER.get(), p_155229_, p_155230_);
    }

}

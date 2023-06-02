package de.joshicodes.jbb.block;

import de.joshicodes.jbb.block.entity.BreakerBlockEntity;
import de.joshicodes.jbb.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BreakerBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN);

    private final BreakerType type;

    public BreakerBlock(final BreakerType type) {
        super(
                Properties.of(Material.METAL)
                        .strength(5.0f)
                        .requiresCorrectToolForDrops()
        );
        this.type = type;

        this.registerDefaultState(
                this.getStateDefinition().any()
                        .setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        if(p_49820_.getPlayer() == null) {
            return this.defaultBlockState();
        }
        Direction direction = p_49820_.getNearestLookingDirection();
        if(!p_49820_.getPlayer().isShiftKeyDown()) {
            direction = direction.getOpposite();
        }
        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {

        switch (type) {
            case STONE -> {
                return BlockEntityInit.STONE_BLOCK_BREAKER.get().create(pos, state);
            }
            case IRON -> {
                return BlockEntityInit.IRON_BLOCK_BREAKER.get().create(pos, state);
            }
            case DIAMOND -> {
                return BlockEntityInit.DIAMOND_BLOCK_BREAKER.get().create(pos, state);
            }
            case NETHERITE -> {
                return BlockEntityInit.NETHERITE_BLOCK_BREAKER.get().create(pos, state);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }


    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState p_153213_, @NotNull BlockEntityType<T> p_153214_) {
        return level.isClientSide() ? null : ($0, $1, $2, blockEntity) -> {
            if (blockEntity instanceof BreakerBlockEntity e) {
                e.tick(type);
            }
        };
    }

    public static enum BreakerType {
        STONE(5, Tiers.STONE, null),
        IRON(2.5, Tiers.IRON, STONE),
        DIAMOND(1, Tiers.DIAMOND, IRON),
        NETHERITE(0.25, Tiers.NETHERITE, DIAMOND);

        private final double speedModifier;
        private final Tiers tiers;

        private final List<Tiers> allowedTiers;

        BreakerType(double speedModifier, Tiers tiers, BreakerType parent) {
            this.speedModifier = speedModifier;
            this.tiers = tiers;
            List<Tiers> list = new ArrayList<>();
            list.add(tiers);
            if(parent != null) {
                list.addAll(parent.getAllowedTiers());
            }
            this.allowedTiers = list;
        }

        public double getSpeedModifier() {
            return speedModifier;
        }

        public List<Tiers> getAllowedTiers() {
            return allowedTiers;
        }

        public boolean canHarvest(BlockState state) {
            if(!state.requiresCorrectToolForDrops()) return true;

            if(state.hasBlockEntity()) return false;  // Do not break block if it has a block entity

            if(tiers.getLevel() >= 1 && state.is(BlockTags.NEEDS_STONE_TOOL)) {
                return true;
            } else if(tiers.getLevel() >= 2 && state.is(BlockTags.NEEDS_IRON_TOOL)) {
                return true;
            } else if(tiers.getLevel() >= 3 && state.is(BlockTags.NEEDS_DIAMOND_TOOL)) {
                return true;
            }

            if(state.getTags().anyMatch((t) -> {
                for (Tiers tiers : allowedTiers) {
                    if(t.equals(tiers.getTag())) return true;
                }
                return false;
            })) {
                return true;
            }

            return state.getTags().anyMatch((t) -> t.equals(Tags.Blocks.STONE));
        }
    }

}

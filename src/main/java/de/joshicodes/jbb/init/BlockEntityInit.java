package de.joshicodes.jbb.init;

import de.joshicodes.jbb.BlockBreakerMod;
import de.joshicodes.jbb.block.entity.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BlockBreakerMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<StoneBreakerBlockEntity>> STONE_BLOCK_BREAKER = BLOCK_ENTITIES.register("stone_block_breaker_entity",
            () -> BlockEntityType.Builder.of(StoneBreakerBlockEntity::new, BlockInit.STONE_BREAKER_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<IronBreakerBlockEntity>> IRON_BLOCK_BREAKER = BLOCK_ENTITIES.register("iron_block_breaker_entity",
            () -> BlockEntityType.Builder.of(IronBreakerBlockEntity::new, BlockInit.IRON_BREAKER_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<DiamondBreakerBlockEntity>> DIAMOND_BLOCK_BREAKER = BLOCK_ENTITIES.register("diamond_block_breaker_entity",
            () -> BlockEntityType.Builder.of(DiamondBreakerBlockEntity::new, BlockInit.DIAMOND_BREAKER_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<NetheriteBreakerBlockEntity>> NETHERITE_BLOCK_BREAKER = BLOCK_ENTITIES.register("netherite_block_breaker_entity",
            () -> BlockEntityType.Builder.of(NetheriteBreakerBlockEntity::new, BlockInit.NETHERITE_BREAKER_BLOCK.get()).build(null));

}

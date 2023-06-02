package de.joshicodes.jbb.init;

import de.joshicodes.jbb.BlockBreakerMod;
import de.joshicodes.jbb.block.BreakerBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BlockBreakerMod.MOD_ID);

    public static final RegistryObject<BreakerBlock> STONE_BREAKER_BLOCK = register("block_breaker_stone", () -> new BreakerBlock(BreakerBlock.BreakerType.STONE), null);
    public static final RegistryObject<BreakerBlock> IRON_BREAKER_BLOCK = register("block_breaker_iron", () -> new BreakerBlock(BreakerBlock.BreakerType.IRON), null);
    public static final RegistryObject<BreakerBlock> DIAMOND_BREAKER_BLOCK = register("block_breaker_diamond", () -> new BreakerBlock(BreakerBlock.BreakerType.DIAMOND), null);
    public static final RegistryObject<BreakerBlock> NETHERITE_BREAKER_BLOCK = register("block_breaker_netherite", () -> new BreakerBlock(BreakerBlock.BreakerType.NETHERITE), null);

    public static <T extends Block> RegistryObject<T> register(String resource, Supplier<T> block, @Nullable Supplier<Item> item) {
        RegistryObject<T> object = BLOCKS.register(resource, block);
        if (item != null) {
            ItemInit.register(resource, item);
        }
        return object;
    }

}

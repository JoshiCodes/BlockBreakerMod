package de.joshicodes.jbb.init;

import de.joshicodes.jbb.BlockBreakerMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ItemInit {

    public static CreativeModeTab TAB_GROUP = new CreativeModeTab(BlockBreakerMod.MOD_ID + ".tab_group") {

        @Override
        public @NotNull ItemStack makeIcon() {
            return random().get().getDefaultInstance();
        }

    };


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BlockBreakerMod.MOD_ID);
    public static final Item.Properties DEFAULT_ITEM_PROPERTIES = new Item.Properties().tab(TAB_GROUP);


    public static final RegistryObject<Item> STONE_BREAKER_ITEM = register("block_breaker_stone", () -> new BlockItem(BlockInit.STONE_BREAKER_BLOCK.get(), DEFAULT_ITEM_PROPERTIES));
    public static final RegistryObject<Item> IRON_BREAKER_ITEM = register("block_breaker_iron", () -> new BlockItem(BlockInit.IRON_BREAKER_BLOCK.get(), DEFAULT_ITEM_PROPERTIES));
    public static final RegistryObject<Item> DIAMOND_BREAKER_ITEM = register("block_breaker_diamond", () -> new BlockItem(BlockInit.DIAMOND_BREAKER_BLOCK.get(), DEFAULT_ITEM_PROPERTIES));
    public static final RegistryObject<Item> NETHERITE_BREAKER_ITEM = register("block_breaker_netherite", () -> new BlockItem(BlockInit.NETHERITE_BREAKER_BLOCK.get(), DEFAULT_ITEM_PROPERTIES));

    public static <T extends Item> RegistryObject<T> register(final String resource, final Supplier<T> item) {
        return ITEMS.register(resource, item);
    }

    public static RegistryObject<Item> random() {
        return ITEMS.getEntries().stream().findAny().orElseThrow(() -> new IllegalStateException("Could not find any items!"));
        //return STONE_BREAKER_ITEM;
    }

}

package de.joshicodes.jbb;

import com.mojang.logging.LogUtils;
import de.joshicodes.jbb.init.BlockEntityInit;
import de.joshicodes.jbb.init.BlockInit;
import de.joshicodes.jbb.init.ItemInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BlockBreakerMod.MOD_ID)
public class BlockBreakerMod {

    public static final String MOD_ID = "jbb";

    private final Logger logger = LogUtils.getLogger();

    public BlockBreakerMod() {

        logger.info("Loading " + MOD_ID + "...");

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockInit.BLOCKS.register(bus);
        BlockEntityInit.BLOCK_ENTITIES.register(bus);

        ItemInit.ITEMS.register(bus);

        logger.info("Finished!");

    }

}

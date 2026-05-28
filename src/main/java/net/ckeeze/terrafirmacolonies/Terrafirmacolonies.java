package net.ckeeze.terrafirmacolonies;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Terrafirmacolonies.MODID)
public class Terrafirmacolonies {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "terrafirmacolonies";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // Create a Deferred Register to hold Blocks which will all be registered under the "terrafirmacolonies" namespace
    public Terrafirmacolonies() {

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
}

package ivkond.mc.mods.boulders.neoforge;

import ivkond.mc.mods.boulders.Boulders;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Boulders.MOD_ID)
public final class BouldersNeoForge {
    private static final Logger LOGGER = LogManager.getLogger(Boulders.MOD_ID);

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, Boulders.MOD_ID);

    public BouldersNeoForge(IEventBus modEventBus) {
        LOGGER.info("Gathering boulders from the void...");

        Boulders.initFeatures((id, feature) ->
                FEATURES.register(id.getPath(), () -> feature)
        );

        FEATURES.register(modEventBus);
    }
}

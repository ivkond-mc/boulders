package ivkond.mc.mods.boulders;

import ivkond.mc.mods.boulders.feature.BoulderFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.function.BiConsumer;

public final class Boulders {
    public static final String MOD_ID = "boulders";

    public static void initFeatures(BiConsumer<ResourceLocation, Feature<?>> consumer) {
        consumer.accept(
                ResourceLocation.fromNamespaceAndPath(Boulders.MOD_ID, "boulder"),
                new BoulderFeature()
        );
    }
}

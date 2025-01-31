package ivkond.mc.mods.boulders.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;

public record BoulderFeatureConfig(
        ResourceLocation material,
        int ratio,
        Radius radius,
        List<ResourceLocation> ores
) implements FeatureConfiguration {
    public static final Codec<BoulderFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance
            .group(
                    ResourceLocation.CODEC
                            .fieldOf("material")
                            .forGetter(BoulderFeatureConfig::material),
                    Codec.intRange(0, 100)
                            .fieldOf("ratio")
                            .forGetter(BoulderFeatureConfig::ratio),
                    Radius.CODEC
                            .fieldOf("radius")
                            .forGetter(BoulderFeatureConfig::radius),
                    Codec.list(ResourceLocation.CODEC, 1, 20)
                            .fieldOf("ores")
                            .forGetter(BoulderFeatureConfig::ores)
            )
            .apply(instance, BoulderFeatureConfig::new)
    );

    public record Radius(int min, int max) {
        private static final int MAX_RADIUS = 16;

        public static final Codec<Radius> CODEC = RecordCodecBuilder.create(instance -> instance
                .group(
                        Codec.intRange(1, MAX_RADIUS)
                                .fieldOf("min")
                                .forGetter(Radius::max),
                        Codec.intRange(1, MAX_RADIUS)
                                .fieldOf("max")
                                .forGetter(Radius::max)
                )
                .apply(instance, Radius::new)
        );
    }
}

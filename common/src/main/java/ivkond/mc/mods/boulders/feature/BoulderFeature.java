package ivkond.mc.mods.boulders.feature;

import ivkond.mc.mods.boulders.Boulders;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BoulderFeature extends Feature<BoulderFeatureConfig> {
    private static final Logger LOGGER = LogManager.getLogger(Boulders.MOD_ID);

    public BoulderFeature() {
        super(BoulderFeatureConfig.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<BoulderFeatureConfig> context) {
        var world = context.level();
        var origin = context.origin();

        if (world.isClientSide()) {
            return false;
        }

        var solidBlockFound = false;
        var testPos = new BlockPos(origin);
        var targetPos = testPos;

        for (int y = origin.getY(); y > world.getMinY(); y--) {
            var downPos = testPos.below();
            var testState = world.getBlockState(downPos);
            if (testState.isRedstoneConductor(world, downPos) && !solidBlockFound) {
                targetPos = testPos = downPos;
                solidBlockFound = true;
            } else {
                testPos = downPos;
            }
        }

        // edge case: if no solid block was found
        if (!solidBlockFound)
            return false;

        placeBoulder(targetPos, context);

        LOGGER.debug("Placing boulder at {}", targetPos);
        return true;
    }

    private void placeBoulder(BlockPos startPos, FeaturePlaceContext<BoulderFeatureConfig> context) {
        var world = context.level();
        var random = context.random();
        var config = context.config();
        var radius = random.nextInt(config.radius().min(), config.radius().max() + 1);
        var movedCenter = startPos.relative(Direction.getRandom(random), random.nextInt(0, Math.max(1, radius - 1)));

        var noise = new ImprovedNoise(random);
        for (BlockPos pos : BlockPos.withinManhattan(movedCenter, radius, radius, radius)) {
            if (Math.sqrt(pos.distSqr(movedCenter)) > radius + noise.noise(pos.getX(), pos.getY(), pos.getZ()))
                continue;
            world.setBlock(pos, getOreOrMaterial(config, random), 0x10);
        }
    }

    private BlockState getOreOrMaterial(BoulderFeatureConfig config, RandomSource random) {
        List<ResourceLocation> ores = config.ores();

        if (random.nextInt(1, 100) < config.ratio()) {
            return BuiltInRegistries.BLOCK.getValue(config.material()).defaultBlockState();
        }

        ResourceLocation id = selectRandomIdOrMaterial(config, random, ores);

        return BuiltInRegistries.BLOCK.getValue(id).defaultBlockState();
    }

    private ResourceLocation selectRandomIdOrMaterial(BoulderFeatureConfig config, RandomSource random, List<ResourceLocation> ores) {
        ResourceLocation id;
        int attempts = 10;
        do {
            id = getRandomFromList(ores, random);
            if (BuiltInRegistries.BLOCK.containsKey(id)) {
                break;
            }
            attempts--;
        } while (attempts > 0);

        if (id == null) {
            id = config.material();
        }
        return id;
    }

    private ResourceLocation getRandomFromList(List<ResourceLocation> list, RandomSource random) {
        return list.get(random.nextInt(list.size()));
    }
}

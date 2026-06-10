package com.davigj.reverse_reverse.core;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class RRConfig {
    public static class Common {
        public final ModConfigSpec.ConfigValue<Boolean> villagersTrade;
        public final ModConfigSpec.ConfigValue<Boolean> retroTrade;
        public final ModConfigSpec.ConfigValue<Boolean> nostalgicTrade;
        public final ModConfigSpec.ConfigValue<Boolean> moonTrade;
        public final ModConfigSpec.ConfigValue<Double> moonFallReduction;

        Common (ModConfigSpec.Builder builder) {
            builder.push("config");
            builder.push("trading");
            villagersTrade = builder.comment("Do villagers trade reverse reverse armor items").define("Villagers trade", true);
            retroTrade = builder.comment("Do villagers trade retro sneakers").define("Retro sneakers trade", true);
            nostalgicTrade = builder.comment("Do villagers trade nostalgic glasses").define("Nostalgic glasses trade", true);
            moonTrade = builder.comment("Do villagers trade moon walkers").define("Moon walkers trade", true);
            builder.pop();
            builder.push("tweaks");
            moonFallReduction = builder.comment("Moon walkers fall damage reduction percentage. Decimal number of 0.0 through 1.0").define("Moon walkers fall reduction", 0.5);
            builder.pop();
            builder.pop();
        }
    }

    static final ModConfigSpec COMMON_SPEC;
    public static final RRConfig.Common COMMON;


    static {
        final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(RRConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}

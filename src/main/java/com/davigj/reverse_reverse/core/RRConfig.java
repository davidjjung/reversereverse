package com.davigj.reverse_reverse.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class RRConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> villagersTrade;
        public final ForgeConfigSpec.ConfigValue<Boolean> retroTrade;
        public final ForgeConfigSpec.ConfigValue<Boolean> nostalgicTrade;
        public final ForgeConfigSpec.ConfigValue<Boolean> moonTrade;
        public final ForgeConfigSpec.ConfigValue<Double> moonFallReduction;

        Common (ForgeConfigSpec.Builder builder) {
            builder.push("config");
            builder.push("trading");
            villagersTrade = builder.comment("Do villagers trade reverse reverse armor items").define("Villagers trade", true);
            retroTrade = builder.comment("Do villagers trade retro sneakers").define("Retro sneakers trade", true);
            nostalgicTrade = builder.comment("Do villagers trade nostalgic glasses").define("Nostalgic glasses trade", true);
            moonTrade = builder.comment("Do armorers trade moon walkers").define("Moon walkers trade", true);
            builder.pop();
            builder.push("tweaks");
            moonFallReduction = builder.comment("Moon walkers fall damage reduction percentage. Decimal number of 0.0 through 1.0").define("Moon walkers fall reduction", 0.25);
            builder.pop();
            builder.pop();
        }
    }

    static final ForgeConfigSpec COMMON_SPEC;
    public static final RRConfig.Common COMMON;


    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(RRConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}

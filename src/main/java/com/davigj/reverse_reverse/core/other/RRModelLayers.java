package com.davigj.reverse_reverse.core.other;

import com.davigj.reverse_reverse.core.ReverseReverse;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class RRModelLayers {
    public static final ModelLayerLocation MOON_WALKERS = register("moon_walkers", "armor");
    public static final ModelLayerLocation RETRO_SNEAKERS = register("retro_sneakers", "armor");

    public static ModelLayerLocation register(String name, String layer) {
        return new ModelLayerLocation(new ResourceLocation(ReverseReverse.MOD_ID, name), layer);
    }
}

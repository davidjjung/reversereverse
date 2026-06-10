package com.davigj.reverse_reverse.core.other;

import com.davigj.reverse_reverse.core.ReverseReverse;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;


@EventBusSubscriber(modid = ReverseReverse.MOD_ID, value = Dist.CLIENT)
public class RRModelLayers {
    public static final ModelLayerLocation MOON_WALKERS = register("moon_walkers", "armor");
    public static final ModelLayerLocation RETRO_SNEAKERS = register("retro_sneakers", "armor");

    public static ModelLayerLocation register(String name, String layer) {
        return new ModelLayerLocation(ReverseReverse.location(name), layer);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
//        event.registerLayerDefinition(RRClientEvents.MOON_WALKERS, ChunkyBootsModel::createBodyLayer);
//        event.registerLayerDefinition(RRClientEvents.RETRO_SNEAKERS, ChunkyBootsModel::createBodyLayer);
    }
}

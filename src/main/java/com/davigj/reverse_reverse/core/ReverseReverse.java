package com.davigj.reverse_reverse.core;

import com.davigj.reverse_reverse.client.model.MoonWalkersModel;
import com.davigj.reverse_reverse.core.other.RRModelLayers;
import com.davigj.reverse_reverse.core.registry.RRParticleTypes;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ReverseReverse.MOD_ID)
public class ReverseReverse {
    public static final String MOD_ID = "reverse_reverse";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    public ReverseReverse() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();
        MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);
        RRParticleTypes.PARTICLE_TYPES.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            bus.addListener(this::registerLayerDefinitions);
        });

        context.registerConfig(ModConfig.Type.COMMON, RRConfig.COMMON_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }

    private void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }

    private void dataSetup(GatherDataEvent event) {

    }

    @OnlyIn(Dist.CLIENT)
    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RRModelLayers.MOON_WALKERS, MoonWalkersModel::createArmorLayer);
        event.registerLayerDefinition(RRModelLayers.RETRO_SNEAKERS, MoonWalkersModel::createArmorLayer);
    }
}
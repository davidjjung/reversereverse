package com.davigj.reverse_reverse.core;

import com.davigj.reverse_reverse.core.registry.RRArmorMaterials;
import com.davigj.reverse_reverse.core.registry.RRItems;
import com.davigj.reverse_reverse.core.registry.RRParticleTypes;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(ReverseReverse.MOD_ID)
public class ReverseReverse {
    public static final String MOD_ID = "reverse_reverse";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    public ReverseReverse(IEventBus bus, ModContainer container) {
        RRItems.ITEMS.register(bus);
        RRArmorMaterials.ARMOR_MATERIALS.register(bus);

        RRParticleTypes.PARTICLE_TYPES.register(bus);

        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::dataSetup);

        container.registerConfig(ModConfig.Type.COMMON, RRConfig.COMMON_SPEC);
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

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(ReverseReverse.MOD_ID, path);
    }
}
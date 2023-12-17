package com.davigj.reverse_reverse.core.registry;

import com.davigj.reverse_reverse.client.particle.MoondustParticle;
import com.davigj.reverse_reverse.core.ReverseReverse;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ReverseReverse.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RRParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ReverseReverse.MOD_ID);

    public static final RegistryObject<SimpleParticleType> MOONDUST = PARTICLE_TYPES.register("moondust", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
        event.register(RRParticleTypes.MOONDUST.get(), MoondustParticle.Factory::new);
    }
}

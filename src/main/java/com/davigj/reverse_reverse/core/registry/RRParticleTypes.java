package com.davigj.reverse_reverse.core.registry;

import com.davigj.reverse_reverse.client.particle.MoondustParticle;
import com.davigj.reverse_reverse.core.ReverseReverse;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid = ReverseReverse.MOD_ID, value = Dist.CLIENT)
public class RRParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, ReverseReverse.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MOONDUST = PARTICLE_TYPES.register("moondust", () -> new SimpleParticleType(true));

    @SubscribeEvent
    public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(RRParticleTypes.MOONDUST.get(), MoondustParticle.Factory::new);
    }
}

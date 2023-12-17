package com.davigj.reverse_reverse.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MoondustParticle extends TextureSheetParticle {

    protected MoondustParticle(ClientLevel level, double xCoordIn, double yCoordIn, double zCoordIn, double motionX, double motionY, double motionZ) {
        super(level, xCoordIn, yCoordIn, zCoordIn);
        this.xd = motionX * 0.1D;
        this.yd = motionY * 0.1D;
        this.zd = motionZ * 0.1D;
        this.quadSize *= 1.5F;
        this.lifetime = 120;
        this.hasPhysics = false;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float alpha = (float)this.age / (float)this.lifetime;
            this.alpha = 1.0F - alpha;

            this.yd = -0.02D;

            this.move(this.xd, this.yd, this.zd);

            if (this.onGround) {
                this.xd *= 0.7F;
                this.zd *= 0.7F;
            }
        }
    }



    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;
        public Factory(SpriteSet sprite) {
            this.spriteSet = sprite;
        }
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MoondustParticle particle = new MoondustParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}


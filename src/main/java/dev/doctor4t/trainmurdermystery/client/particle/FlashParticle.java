package dev.doctor4t.trainmurdermystery.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;

public class FlashParticle extends SpriteBillboardParticle {
    protected FlashParticle(ClientWorld world, double x, double y, double z,
                            double vx, double vy, double vz,
                            float scale) {
        super(world, x, y, z, vx, vy, vz);

        this.maxAge = 3;
        this.scale = scale;
        this.setVelocity(vx, vy, vz);

        this.red = 1f;
        this.green = 1f;
        this.blue = 1f;
        this.alpha = 1f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    protected int getBrightness(float tint) {
        return 0xF000F0;
    }

    @Override
    public void tick() {
        super.tick();
        this.alpha = 1.0f - ((float) this.age / this.maxAge);
    }

    public static class GunshotFactory<DefaultParticleType extends ParticleEffect> implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public GunshotFactory(SpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            FlashParticle particle = new FlashParticle(world, x, y, z, vx, vy, vz, .2f);
            particle.setSprite(this.sprites);
            return particle;
        }
    }

    public static class ExplosionFactory<DefaultParticleType extends ParticleEffect> implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public ExplosionFactory(SpriteProvider sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            FlashParticle particle = new FlashParticle(world, x, y, z, vx, vy, vz, .4f);
            particle.setSprite(this.sprites);
            return particle;
        }
    }
}

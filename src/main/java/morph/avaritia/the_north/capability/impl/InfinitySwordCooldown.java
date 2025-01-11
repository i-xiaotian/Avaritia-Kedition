package morph.avaritia.the_north.capability.impl;

import morph.avaritia.the_north.capability.api.IInfinitySwordCooldown;

public class InfinitySwordCooldown implements IInfinitySwordCooldown {

    private long cooldown;

    private long lastCalculateMillis;

    @Override
    public void setCooldown(final long millis) {
        this.cooldown = millis;
    }

    @Override
    public long getCooldown() {
        return this.cooldown;
    }

    @Override
    public void tick() {
        if (cooldown <= 0) return;
        if (this.lastCalculateMillis == 0) this.lastCalculateMillis = System.currentTimeMillis();
        this.cooldown = this.cooldown - (System.currentTimeMillis() - this.lastCalculateMillis);
        if (this.cooldown < 0) this.cooldown = 0;
    }
}

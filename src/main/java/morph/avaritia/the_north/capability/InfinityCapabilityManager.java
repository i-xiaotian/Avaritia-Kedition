package morph.avaritia.the_north.capability;

import morph.avaritia.the_north.capability.api.IInfinitySwordCooldown;
import morph.avaritia.the_north.capability.impl.InfinitySwordCooldown;
import morph.avaritia.the_north.capability.nbt.InfinitySwordCooldownNbt;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class InfinityCapabilityManager {

    @CapabilityInject(IInfinitySwordCooldown.class)
    public static Capability<IInfinitySwordCooldown> INFINITY_SWORD_COOLDOWN_CAP;

    public static void register() {
        CapabilityManager.INSTANCE.register(IInfinitySwordCooldown.class, new InfinitySwordCooldownNbt(), InfinitySwordCooldown::new);
    }

}

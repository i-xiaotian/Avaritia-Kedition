package morph.avaritia.the_north.capability.nbt;

import morph.avaritia.the_north.capability.api.IInfinitySwordCooldown;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import org.jetbrains.annotations.Nullable;

public class InfinitySwordCooldownNbt implements Capability.IStorage<IInfinitySwordCooldown> {

    @Nullable
    @Override
    public NBTBase writeNBT(final Capability<IInfinitySwordCooldown> capability, final IInfinitySwordCooldown IInfinitySwordCooldown, final EnumFacing enumFacing) {
        return new NBTTagLong(IInfinitySwordCooldown.getCooldown());
    }

    @Override
    public void readNBT(final Capability<IInfinitySwordCooldown> capability, final IInfinitySwordCooldown IInfinitySwordCooldown, final EnumFacing enumFacing, final NBTBase nbtBase) {
        IInfinitySwordCooldown.setCooldown(((NBTTagLong) nbtBase).getLong());
    }
}

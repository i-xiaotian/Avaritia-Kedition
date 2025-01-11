package morph.avaritia.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DamageSourceInfinitySwordOp extends EntityDamageSource {

    public DamageSourceInfinitySwordOp(Entity source) {
        super("infinityop", source);
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entity) {
        ItemStack itemstack = damageSourceEntity instanceof EntityLivingBase ? ((EntityLivingBase) damageSourceEntity).getHeldItem(EnumHand.MAIN_HAND) : null;
        String s = "death.attack.op.infinity";
        return new TextComponentTranslation(s, entity.getDisplayName(), itemstack.getDisplayName());
    }

    @Override
    public boolean isDifficultyScaled() {
        return false;
    }

}

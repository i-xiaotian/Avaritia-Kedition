package morph.avaritia.compat.bloodmagic;

import WayofTime.bloodmagic.ConfigHandler;
import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.core.data.SoulNetwork;
import WayofTime.bloodmagic.item.ItemBindableBase;
import WayofTime.bloodmagic.orb.BloodOrb;
import WayofTime.bloodmagic.orb.IBloodOrb;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import WayofTime.bloodmagic.util.helper.PlayerHelper;
import WayofTime.bloodmagic.util.helper.TextHelper;
import morph.avaritia.Avaritia;
import morph.avaritia.api.registration.IModelRegister;
import morph.avaritia.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemArmokOrb extends ItemBindableBase implements IBloodOrb, IModelRegister {
    public ItemArmokOrb() {
        setMaxStackSize(1);
        setMaxDamage(0);
        setCreativeTab(Avaritia.tab);
        setTranslationKey("avaritia:armok_orb");
        setRegistryName("armok_orb");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add(TextHelper.localizeEffect("tooltip.armok.desc"));

        if (flag.isAdvanced() && !stack.isEmpty())
            tooltip.add(TextHelper.localizeEffect("tooltip.bloodmagic.orb.owner", stack.getItem().getRegistryName().toString()));

        super.addInformation(stack, world, tooltip, flag);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (stack.isEmpty())
            return ActionResult.newResult(EnumActionResult.FAIL, stack);

        if (world == null)
            return super.onItemRightClick(world, player, hand);

        if (PlayerHelper.isFakePlayer(player))
            return super.onItemRightClick(world, player, hand);

        if (!stack.hasTagCompound())
            return super.onItemRightClick(world, player, hand);

        Binding binding = getBinding(stack);
        if (binding == null)
            return super.onItemRightClick(world, player, hand);

        if (world.isRemote)
            return super.onItemRightClick(world, player, hand);

        SoulNetwork ownerNetwork = NetworkHelper.getSoulNetwork(binding);
        if (binding.getOwnerId().equals(player.getGameProfile().getId()))
            ownerNetwork.setOrbTier(666);

        // Add LP to owner's network
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return ModItems.COSMIC_RARITY;
    }

    @Override
    public boolean hasContainerItem() {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        return stack.copy();
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote && entity instanceof EntityPlayer) {
            NBTTagCompound itemTag = stack.getTagCompound();
            Binding binding = getBinding(stack);
            if (itemTag == null || binding == null)
                return;

            SoulNetwork ownerNetwork = NetworkHelper.getSoulNetwork(binding);
            ownerNetwork.setCurrentEssence(getMaxEssence());
        }
    }

    public int getMaxEssence() {
        return 1000000000;
    }

    @Nullable
    @Override
    public BloodOrb getOrb(ItemStack stack) {
        return new BloodOrb("bloodmagic:armok", ConfigHandler.general.enableTierSixEvenThoughThereIsNoContent ? 6 : 5, 1000000000, 1000000000);
    }

    @Override
    public void registerModels() {
        ModelResourceLocation orb = new ModelResourceLocation("avaritia:resource", "type=armok_orb");
        ModelLoader.registerItemVariants(ModItems.armok_orb, orb);
        ModelLoader.setCustomMeshDefinition(ModItems.armok_orb, (ItemStack stack) -> orb);
    }
}

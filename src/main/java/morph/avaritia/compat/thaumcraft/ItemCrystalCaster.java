package morph.avaritia.compat.thaumcraft;

import morph.avaritia.Avaritia;
import morph.avaritia.api.registration.IModelRegister;
import morph.avaritia.init.ModItems;
import morph.avaritia.util.TextUtils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.casters.FocusEngine;
import thaumcraft.api.casters.FocusPackage;
import thaumcraft.api.casters.IFocusBlockPicker;
import thaumcraft.api.casters.IFocusElement;
import thaumcraft.common.items.casters.CasterManager;
import thaumcraft.common.items.casters.ItemCaster;
import thaumcraft.common.items.casters.ItemFocus;

import java.util.List;

public class ItemCrystalCaster extends ItemCaster implements IModelRegister {
    public ItemCrystalCaster() {
        super("crystal_caster", 0);
    }

    @Override
    public boolean consumeVis(ItemStack is, EntityPlayer player, float amount, boolean crafting, boolean sim) {
        return true;
    }

    public EnumRarity getRarity(ItemStack stack) {
        return ModItems.COSMIC_RARITY;
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal("item.avaritia:crystal_caster.name");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack focusStack = this.getFocusStack(player.getHeldItem(hand));
        ItemFocus focus = this.getFocus(player.getHeldItem(hand));
        if (focus != null) {
            CasterManager.setCooldown(player, focus.getActivationTime(focusStack));
            FocusPackage core = ItemFocus.getPackage(focusStack);
            if (player.isSneaking()) {
                for (IFocusElement fe : core.nodes) {
                    if (fe instanceof IFocusBlockPicker && player.isSneaking()) {
                        return new ActionResult<>(EnumActionResult.PASS, player.getHeldItem(hand));
                    }
                }
            }

            player.swingArm(hand);
            if (!world.isRemote && this.consumeVis(player.getHeldItem(hand), player, focus.getVisCost(focusStack), false, false)) {
                FocusEngine.castFocusPackage(player, core);
                player.getCooldownTracker().setCooldown(this, 10);
                return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
            }
        }

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void registerModels() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation("avaritia:crystal_caster", "inventory"));
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if ((tab == Avaritia.tab) || (tab == CreativeTabs.SEARCH)) {
            items.add(new ItemStack(this));
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        ItemStack focus = this.getFocusStack(stack);
        if (focus != null && !focus.isEmpty()) {
            String text = "0.0" + " " + I18n.translateToLocal("item.Focus.cost1");

            tooltip.add(TextUtils.makeFabulous(I18n.translateToLocal("tc.vis.cost") + " " + text));
        }

        if (this.getFocus(stack) != null) {
            tooltip.add(String.valueOf(TextFormatting.BOLD) + TextFormatting.ITALIC + TextFormatting.GREEN + this.getFocus(stack).getItemStackDisplayName(this.getFocusStack(stack)));
            this.getFocus(stack).addFocusInformation(this.getFocusStack(stack), worldIn, tooltip, flagIn);
        }

    }
}

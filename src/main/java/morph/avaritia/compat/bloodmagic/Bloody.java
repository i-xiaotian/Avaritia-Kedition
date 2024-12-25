package morph.avaritia.compat.bloodmagic;

import WayofTime.bloodmagic.ConfigHandler;
import morph.avaritia.compat.Compat;
import morph.avaritia.init.ModItems;
import morph.avaritia.recipe.ExtremeCraftingManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Bloody {

    public static void preInit() throws Compat.ItemNotFoundException {
        ModItems.armok_orb = ModItems.registerItem(new ItemArmokOrb());
    }

    public static void init() throws Compat.ItemNotFoundException {
        ItemStack archOrb = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation("bloodmagic", "blood_orb")));
        NBTTagCompound tag = new NBTTagCompound();
        if(!ConfigHandler.general.enableTierSixEvenThoughThereIsNoContent)
            tag.setString("orb", "bloodmagic:archmage");
        else
            tag.setString("orb", "bloodmagic:transcendent");
        archOrb.setTagCompound(tag);

        ExtremeCraftingManager.addRecipe(new ItemStack(ModItems.armok_orb, 1),
                "   III   ",
                "  IOIOI  ",
                "  IIXII  ",
                " NIOIOIN ",
                "NNNIIINNN",
                " NNNNNNN ",
                "   NNN   ",
                'I', new ItemStack(ModItems.resource, 1, 6),
                'X', new ItemStack(ModItems.resource, 1, 5),
                'N', new ItemStack(ModItems.resource, 1, 4),
                'O', archOrb);
    }
}

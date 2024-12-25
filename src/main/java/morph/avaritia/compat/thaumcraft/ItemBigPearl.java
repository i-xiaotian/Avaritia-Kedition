package morph.avaritia.compat.thaumcraft;

import morph.avaritia.Avaritia;
import morph.avaritia.api.registration.IModelRegister;
import morph.avaritia.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBigPearl extends Item implements IModelRegister {

    public ItemBigPearl() {
        setTranslationKey("avaritia:extremely_primordial_pearl");
        setRegistryName("extremely_primordial_pearl");
        setMaxStackSize(1);
        setCreativeTab(Avaritia.tab);
        this.setContainerItem(this);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    @SideOnly (Side.CLIENT)
    public void registerModels() {
        ModelResourceLocation pearl = new ModelResourceLocation("avaritia:resource", "type=extremely_primordial_pearl");
        ModelLoader.registerItemVariants(ModItems.extremely_primordial_pearl, pearl);
        ModelLoader.setCustomMeshDefinition(ModItems.extremely_primordial_pearl, (ItemStack stack) -> pearl);
    }
}

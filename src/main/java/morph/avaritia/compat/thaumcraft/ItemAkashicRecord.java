package morph.avaritia.compat.thaumcraft;

import morph.avaritia.Avaritia;
import morph.avaritia.api.registration.IModelRegister;
import morph.avaritia.init.ModItems;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.Collection;

public class ItemAkashicRecord extends Item implements IModelRegister {

    public ItemAkashicRecord() {
        setTranslationKey("avaritia:akashic_record");
        setRegistryName("akashic_record");
        setMaxStackSize(1);
        setCreativeTab(Avaritia.tab);

    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if(!world.isRemote) {
            Collection<ResearchCategory> rc = ResearchCategories.researchCategories.values();
            for (ResearchCategory cat : rc) {
                ResearchManager.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, cat, 999);
                ResearchManager.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, cat, 999);
            }
        }

        return super.onItemRightClick(world, player, hand);
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    public void registerModels() {
        ModelResourceLocation curio = new ModelResourceLocation("avaritia:resource", "type=akashic_record");
        ModelLoader.registerItemVariants(ModItems.akashic_record, curio);
        ModelLoader.setCustomMeshDefinition(ModItems.akashic_record, (ItemStack stack) -> curio);
    }
}
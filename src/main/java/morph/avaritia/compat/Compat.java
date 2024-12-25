package morph.avaritia.compat;

import morph.avaritia.handler.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Compat {

	public static boolean tweak = Loader.isModLoaded("crafftteaker");
	public static boolean ae2 = Loader.isModLoaded("appliedenergistics2") && ConfigHandler.ae2;
	public static boolean thaumic = Loader.isModLoaded("thaumcraft") && ConfigHandler.thaumic;
	//exu = Loader.isModLoaded("ExtraUtilities") && Config.exu;
	//ic2 = Loader.isModLoaded("IC2") && Config.ic2;
	public static boolean botan = Loader.isModLoaded("botania") && ConfigHandler.botan;
	public static boolean blood = Loader.isModLoaded("bloodmagic") && ConfigHandler.blood;
	//bigReactors = Loader.isModLoaded("BigReactors") && Config.bigReactors;
	//pe = Loader.isModLoaded("ProjectE") && Config.pe;
	//mfr = Loader.isModLoaded("MineFactoryReloaded") && Config.mfr;
	//am2 = Loader.isModLoaded("arsmagica2") && Config.am2;
	public static boolean forestry = Loader.isModLoaded("forestry") && ConfigHandler.forestry;
	public static boolean te = Loader.isModLoaded("thermalexpansion") && ConfigHandler.te;
	public static void compatifyPreInit(){
	//		if(Config.craftingOnly)
	//			return;
		/*	if(Loader.isModLoaded("magicalcrops") && Config.magicrops){
				try {
					Item meat = getItem("magicalcrops", "magicalcrops_RawMeat");
					Item crop = getItem("magicalcrops", "magicalcrops_CropProduce");
					OreDictionary.registerOre("cropBlackberry", new ItemStack(crop, 1, 0));
					OreDictionary.registerOre("cropBlueberry", new ItemStack(crop, 1, 1));
					OreDictionary.registerOre("cropChilipepper", new ItemStack(crop, 1, 2));
					OreDictionary.registerOre("cropCucumber", new ItemStack(crop, 1, 3));
					OreDictionary.registerOre("cropGrape", new ItemStack(crop, 1, 4));
					OreDictionary.registerOre("cropRaspberry", new ItemStack(crop, 1, 5));
					OreDictionary.registerOre("cropStrawberry", new ItemStack(crop, 1, 6));
					OreDictionary.registerOre("cropCorn", new ItemStack(crop, 1, 7));
					OreDictionary.registerOre("cropTomato", new ItemStack(crop, 1, 8));
					OreDictionary.registerOre("rawMutton", new ItemStack(meat, 1, 0));
					OreDictionary.registerOre("rawCalamari", new ItemStack(meat, 1, 1));
				}
				catch (Throwable e){
					Lumberjack.log(Level.INFO, e, "Avaritia got bored of waiting for magical crops to grow.");
				}
			}

			if(forestry){
				try {
					Ranger.stopForestFires();
				}
				catch (Throwable e){
					Lumberjack.log(Level.INFO, e, "Avaritia got stung by a bee.");
					forestry = false;
				}
			}
/*
			if(Loader.isModLoaded("witchery") && Config.witch){
				try {
					Item ingredient = getItem("witchery", "ingredient");
					Grinder.catalyst.getInput().add(new ItemStack(ingredient, 1, 150));

					Block egg = getBlock("witchery", "infinityegg");

					ExtremeCraftingManager.getInstance().addExtremeShapedOreRecipe(new ItemStack(egg, 1, 0),
							"   NNN   ",
							"  NNNNN  ",
							"  NNNNN  ",
							" NNNINNN ",
							"NNNIIINNN",
							"NNIIEIINN",
							"NNNIIINNN",
							" NNNINNN ",
							"  NNNNN  ",
							'N', new ItemStack(LudicrousItems.resource, 1, 4),
							'E', new ItemStack(Items.egg),
							'I', new ItemStack(LudicrousItems.resource, 1, 6));
				}
				catch (Throwable e){
					Lumberjack.log(Level.INFO, e, "Avaritia suffered from Curse of the Incompatibility.");
				}
			}
*/
		}

	public static Block getBlock(String mod, String block) throws ItemNotFoundException {
		Block target = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(mod, block));
		if (target == null) {
			throw new ItemNotFoundException(mod, block);
		}
		return target;
	}

	public static Item getItem(String mod, String item) throws ItemNotFoundException {
		Item target = ForgeRegistries.ITEMS.getValue(new ResourceLocation(mod, item));
		if (target == null) {
			throw new ItemNotFoundException(mod, item);
		}
		return target;
	}

	public static class ItemNotFoundException extends Exception {
		public ItemNotFoundException(String mod, String item) {
			super("Unable to find " + item + " in mod " + mod + "! Are you using the correct version of the mod?");
		}
	}
}

package morph.avaritia.integration.jei;

import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.recipes.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import morph.avaritia.recipe.extreme.ExtremeShapelessRecipe;
import net.minecraft.item.ItemStack;

/**
 * Created by brandon3055 on 17/02/2017.
 */
public class ExtremeShapelessRecipeWrapper extends BlankRecipeWrapper {

	private final ExtremeShapelessRecipe recipe;

	public ExtremeShapelessRecipeWrapper(ExtremeShapelessRecipe recipe) {
		this.recipe = recipe;
		for (ItemStack itemStack : this.recipe.recipeItems) {
			if (itemStack != null && itemStack.getCount() != 1) {
				itemStack.setCount(1);
			}
		}
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		List<ItemStack> recipeItems = recipe.recipeItems;
		ItemStack recipeOutput = recipe.getRecipeOutput();
		try {
			ingredients.setInputs(ItemStack.class, recipeItems);
			if (recipeOutput != null) {
				ingredients.setOutput(ItemStack.class, recipeOutput);
			}
		}
		catch (RuntimeException e) {
			String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipeItems, recipeOutput);
			throw new BrokenCraftingRecipeException(info, e);
		}
	}
}
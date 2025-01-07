package morph.avaritia.recipe;

import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.function.BooleanSupplier;

/**
 * Created by keletu on ??/??/2023.
 */
public class OreExistsSafeConditionalFactory implements IConditionFactory {

    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        String ore = JsonUtils.getString(json, "ore");
        return () -> OreDictionary.doesOreNameExist(ore) && OreDictionary.getOres(ore).size() > 0 && !OreDictionary.getOres(ore).get(0).isEmpty();
    }
}
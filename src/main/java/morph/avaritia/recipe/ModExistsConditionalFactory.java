package morph.avaritia.recipe;

import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;

import java.util.function.BooleanSupplier;

/**
 * Created by keletu on ??/??/2023.
 */
public class ModExistsConditionalFactory implements IConditionFactory {

    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        String mod = JsonUtils.getString(json, "mod");
        return () -> Loader.isModLoaded(mod);
    }
}
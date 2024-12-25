package morph.avaritia.compat.botania;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.model.Models;
import vazkii.botania.api.subtile.signature.SubTileSignature;

public class Signature implements SubTileSignature {
    String name;
    Models icon;
    public Signature(String nombre){
        name = nombre;
    }
    @Override
    public String getUnlocalizedNameForStack(ItemStack item){
        return "avaritia.flower." + name;
    }
    @Override
    public String getUnlocalizedLoreTextForStack(ItemStack item){
        return "tile.avaritia.flower." + name + ".lore";
    }
}
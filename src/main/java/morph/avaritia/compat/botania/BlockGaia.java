package morph.avaritia.compat.botania;

import morph.avaritia.Avaritia;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockGaia extends Block {

    public BlockGaia() {
        super(Material.IRON);
        setRegistryName("gaia_block");
        setTranslationKey(Avaritia.MOD_ID + ".gaia_block");
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 3);
        setHardness(50.0F);
        setResistance(2000.0F);
        setCreativeTab(Avaritia.tab);
    }

    @Override
    public boolean isBeaconBase(IBlockAccess world, BlockPos pos, BlockPos beacon) {
        return true;
    }

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
        return false;
    }
}
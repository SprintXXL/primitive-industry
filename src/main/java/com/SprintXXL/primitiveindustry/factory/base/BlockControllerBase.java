package com.SprintXXL.primitiveindustry.factory.base;

import com.SprintXXL.primitiveindustry.PrimitiveIndustry;
import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitivemultiblocks.API.MultiblockAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import static com.SprintXXL.primitiveindustry.Reference.MODID;
import static com.SprintXXL.primitiveindustry.factory.data.structure.StructureType.MULTIBLOCK;
import static com.SprintXXL.primitivemultiblocks.API.MultiblockAPI.isFormationHammer;

public class BlockControllerBase extends Block {

    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    private final Factory factory;

    public BlockControllerBase(Factory factory) {
        super(Material.ANVIL);

        this.factory = factory;

        String name = factory.getControllerName();

        setRegistryName(MODID, name);
        setTranslationKey(MODID + "." + name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setDefaultState(blockState.getBaseState().withProperty(ACTIVE, false));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ACTIVE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(ACTIVE, meta == 1);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(ACTIVE) ? 1 : 0;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {

        TileEntityFactoryBase tile = new TileEntityFactoryBase();

        tile.setFactory(factory);
        return tile;
    }

    @Override
    public boolean onBlockActivated(
            World worldIn,
            BlockPos pos,
            IBlockState state,
            EntityPlayer playerIn,
            EnumHand hand,
            EnumFacing facing,
            float hitX,
            float hitY,
            float hitZ
    ) {

        if (worldIn.isRemote) {
            return true;
        }

        if (hand != EnumHand.MAIN_HAND) {
            return true;
        }

        if (isFormationHammer(playerIn.getHeldItemMainhand())) {
            return true;
        }

        if (factory.getStructureType() == MULTIBLOCK) {

            boolean formed = MultiblockAPI.isFormed(worldIn, pos, factory.getID());

            if (!formed) {
                playerIn.sendMessage(new TextComponentString("Multiblock is not formed yet"));
                return true;
            }
        }
        playerIn.openGui(
                PrimitiveIndustry.INSTANCE,
                0,
                worldIn,
                pos.getX(),
                pos.getY(),
                pos.getZ()
        );
        return true;
    }
}

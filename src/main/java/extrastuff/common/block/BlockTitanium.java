package extrastuff.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockTitanium extends Block {
	public IIcon[] icons = new IIcon[1];

	public BlockTitanium(Material material) {
		super(material);
	}

	public BlockTitanium() {
		this(Material.rock);
		this.setHardness(5.0F);
		// this.setResistance();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(Block.soundTypeMetal);
		this.setHarvestLevel("pickaxe", 3);
		this.setBlockName("titaniumBlock");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		icons[0] = register.registerIcon("extrastuff:TitaniumBlock");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[0];
	}
}

package extrastuff.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrastuff.common.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemTitaniumIngot extends Item {
	public IIcon[] icons = new IIcon[1];

	public ItemTitaniumIngot() {
		maxStackSize = 64;
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName(Reference.MOD_ID + ":titaniumIngot");
		this.setTextureName("TitaniumIngot");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		icons[0] = register.registerIcon("extrastuff:TitaniumIngot");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[0];
	}
}

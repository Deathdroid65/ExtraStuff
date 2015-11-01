package extrastuff.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrastuff.common.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemEnergyCable extends Item {
	public IIcon[] icons = new IIcon[1];

	public ItemEnergyCable() {
		maxStackSize = 64;
		setUnlocalizedName(Reference.MOD_ID + ":itemEnergyCable");
		this.setTextureName("Cable");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		icons[0] = register.registerIcon(Reference.MOD_ID + ":itemEnergyCable");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return icons[0];
	}
}

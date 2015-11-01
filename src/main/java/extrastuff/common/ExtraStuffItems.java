package extrastuff.common;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import extrastuff.common.item.ItemEnergyCable;
import extrastuff.common.item.ItemTitaniumIngot;
import extrastuff.common.reference.Reference;
import net.minecraft.item.Item;

@ObjectHolder(Reference.MOD_ID)
public class ExtraStuffItems {
	public static final Item titaniumIngot = new ItemTitaniumIngot();
	public static final Item energyCable = new ItemEnergyCable();

	public static void register() {
		GameRegistry.registerItem(titaniumIngot, Reference.MOD_ID + ":titaniumIngot");
		GameRegistry.registerItem(energyCable, Reference.MOD_ID + ":itemEnergyCable");
	}
}

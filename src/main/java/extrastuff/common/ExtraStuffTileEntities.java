package extrastuff.common;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import extrastuff.common.tileentity.TileEntityCable;
import extrastuff.common.reference.Reference;

@ObjectHolder(Reference.MOD_ID)
public class ExtraStuffTileEntities {

	public static void register() {
		GameRegistry.registerTileEntity(TileEntityCable.class, Reference.MOD_ID + ":Cable");
	}
}

package extrastuff.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import extrastuff.client.render.ItemRenderCable;
import extrastuff.client.render.TileEntityRenderCable;
import extrastuff.common.CommonProxy;
import extrastuff.common.ExtraStuffItems;
import extrastuff.common.tileentity.TileEntityCable;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerProxies() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new TileEntityRenderCable());
		MinecraftForgeClient.registerItemRenderer(ExtraStuffItems.energyCable, new ItemRenderCable());
	}
}

package extrastuff.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import extrastuff.client.render.TileEntityRenderCable;
import extrastuff.common.CommonProxy;
import extrastuff.common.tileentity.TileEntityCable;
import extrastuff.common.tileentity.TileEntityPipe;
import extrastuff.client.render.TileEntityRenderPipe;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerProxies() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new TileEntityRenderPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new TileEntityRenderCable());
	}
}

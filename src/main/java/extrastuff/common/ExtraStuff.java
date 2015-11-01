package extrastuff.common;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import extrastuff.common.multipart.PartRegister;
import extrastuff.common.reference.Reference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "after:ForgeMultiPart")
public class ExtraStuff {
	@Instance(Reference.MOD_ID)
	public static ExtraStuff instance;

	@SidedProxy(clientSide = "extrastuff.client.ClientProxy", serverSide = "extrastuff.common.CommonProxy")
	public static CommonProxy proxy;

	public static Logger logger = LogManager.getLogger(Reference.MOD_NAME);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ExtraStuffBlocks.register();
		new PartRegister().register(); // Multipart
		ExtraStuffItems.register();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ExtraStuffTileEntities.register();
		proxy.registerProxies();
		ExtraStuffRecipes.register();
	}
}
package extrastuff.common;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import extrastuff.common.block.ExampleBlock;
import extrastuff.common.multipart.PartRegister;
import extrastuff.common.reference.Reference;
import net.minecraft.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "after:ForgeMultiPart")
public class ExtraStuff {
	@Instance(Reference.MOD_ID)
	public static ExtraStuff instance;

	@SidedProxy(clientSide = "extrastuff.client.ClientProxy", serverSide = "extrastuff.common.CommonProxy")
	public static CommonProxy proxy;

	public static Logger logger = LogManager.getLogger("ExtraStuff");



	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ExtraStuffBlocks.register();

		// Multipart
		new PartRegister().register();

		ExtraStuffItems.register();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ExtraStuffTileEntities.register();
		proxy.registerProxies();
		ExtraStuffRecipes.register();
	}
}
package extrastuff.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import extrastuff.common.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ExtraStuff {
	@Instance(Reference.MOD_ID)
	public static ExtraStuff instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ExtraStuffBlocks.register();
		ExtraStuffItems.register();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ExtraStuffRecipes.register();
	}
}
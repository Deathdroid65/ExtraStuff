package extrastuff.common;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import extrastuff.common.block.BlockTitanium;
import extrastuff.common.reference.Reference;
import net.minecraft.block.Block;

@ObjectHolder(Reference.MOD_ID)
public class ExtraStuffBlocks {
	public static final Block titaniumBlock = new BlockTitanium();

	public static void register() {
		GameRegistry.registerBlock(titaniumBlock, "titaniumBlock");
	}
}

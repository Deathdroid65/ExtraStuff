package extrastuff.common.multipart;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.TMultiPart;
import cpw.mods.fml.common.registry.GameRegistry;
import extrastuff.common.block.BlockCable;
import extrastuff.common.block.ExampleBlock;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.Arrays;

public class PartRegister implements IPartFactory, IPartConverter {
	public static Block exampleBlock = new ExampleBlock();
	public static Block energyCable = new BlockCable();

	public void register() {
		GameRegistry.registerBlock(exampleBlock, "multipartExample");
		GameRegistry.registerBlock(energyCable, "energyCable");

		MultiPartRegistry.registerConverter(this);
		MultiPartRegistry.registerParts(this, new String[]{
				"myBlockName",
				"energyCable"
		});
	}

	@Override
	public Iterable<Block> blockTypes() {
		return Arrays.asList(exampleBlock, energyCable);
	}

	@Override
	public TMultiPart convert(World world, BlockCoord pos) {
		Block b = world.getBlock(pos.x, pos.y, pos.z);
		if(b == exampleBlock) return new ExamplePart();
		if(b == energyCable) return new PartCable();
		return null;
	}

	@Override
	public TMultiPart createPart(String name, boolean client) {
		if(name.equals("myBlockName"))return new ExamplePart();
		if(name.equals("energyCable"))return new PartCable();
		return null;
	}
}

package extrastuff.common.multipart;

import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.TMultiPart;
import cpw.mods.fml.common.registry.GameRegistry;
import extrastuff.common.block.BlockEnergyCable;
import extrastuff.common.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.util.Arrays;

public class PartRegister implements IPartFactory, IPartConverter {
	public static Block energyCable = new BlockEnergyCable();

	public void register() {
		GameRegistry.registerBlock(energyCable, Reference.MOD_ID + ":energyCable");
		MultiPartRegistry.registerConverter(this);
		MultiPartRegistry.registerParts(this, new String[]{
				Reference.MOD_ID + ":energyCable"
		});
	}

	@Override
	public Iterable<Block> blockTypes() {
		return Arrays.asList(energyCable);
	}

	@Override
	public TMultiPart convert(World world, BlockCoord pos) {
		Block b = world.getBlock(pos.x, pos.y, pos.z);;
		if(b == energyCable) return new PartCable();
		return null;
	}

	@Override
	public TMultiPart createPart(String name, boolean client) {
		if(name.equals(Reference.MOD_ID + ":energyCable"))return new PartCable();
		return null;
	}
}

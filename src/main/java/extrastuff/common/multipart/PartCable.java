package extrastuff.common.multipart;

import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.minecraft.McMetaPart;
import extrastuff.common.reference.Reference;
import net.minecraft.block.Block;

public class PartCable extends McMetaPart {
	@Override
	public String getType() {
		return Reference.MOD_ID + ":energyCable";
	}

	@Override
	public Cuboid6 getBounds() {
		float pixel = 1F / 16F;
		return new Cuboid6(5 * pixel, 5 * pixel, 5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel);
	}

	@Override
	public Block getBlock() {
		return PartRegister.energyCable;
	}
}

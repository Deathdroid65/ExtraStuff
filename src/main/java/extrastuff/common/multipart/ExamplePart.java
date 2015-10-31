package extrastuff.common.multipart;

import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.minecraft.McMetaPart;
import net.minecraft.block.Block;

public class ExamplePart extends McMetaPart {

	@Override
	public String getType() {
		return "myBlockName";
	}

	@Override
	public Cuboid6 getBounds() {
		return new Cuboid6(0.2F, 0F, 0.2F, 0.8F, 1F, 0.8F);
	}

	@Override
	public Block getBlock() {
		return PartRegister.exampleBlock;
	}
}

package extrastuff.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ExampleBlock extends Block {

	public ExampleBlock() {
		super(Material.ground);
		this.setBlockName("multipartExample");
		this.setBlockTextureName("redstone_block");
		this.setBlockBounds(0.2F, 0F, 0.2F, 0.8F, 1F, 0.8F);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}

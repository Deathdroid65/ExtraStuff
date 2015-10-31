package extrastuff.common.block;

import extrastuff.common.reference.Reference;
import extrastuff.common.tileentity.TileEntityPipe;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockPipe extends BlockContainer {

	public BlockPipe() {
		super(Material.ground);

		float pixel = 1F / 16F;
		// Default - this.setBlockBounds(0, 0, 0, 1, 1, 1);
		// Could enter the values manually
		// minX, minY, minZ, maxX, maxY, maxZ
		this.setBlockBounds(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockName(Reference.MOD_ID +":pipeBlock");
		this.useNeighborBrightness = true;
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		return getBounds(world, x, y, z);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return getBounds(world, x, y, z);
	}

	private AxisAlignedBB getBounds(World world, int x, int y, int z) {
		TileEntityPipe pipe = (TileEntityPipe) world.getTileEntity(x, y, z);
		float pixel = 1F / 16F;
		// UP, DOWN, NORTH, EAST, SOUTH, WEST
		if(pipe != null) {
			float minX = 11 * pixel / 2 - (pipe.connections[5] != null ? (11 * pixel / 2) : 0);		// WEST
			float maxX = 1 - 11 * pixel / 2 + (pipe.connections[3] != null ? (11 * pixel / 2) : 0); // EAST
			float minY = 11 * pixel / 2 - (pipe.connections[1] != null ? (11 * pixel / 2) : 0); 	// DOWN
			float maxY = 1 - 11 * pixel / 2 + (pipe.connections[0] != null ? (11 * pixel / 2) : 0); // UP
			float minZ = 11 * pixel / 2 - (pipe.connections[2] != null ? (11 * pixel / 2) : 0); 	// NORTH
			float maxZ = 1 - 11 * pixel / 2 + (pipe.connections[4] != null ? (11 * pixel / 2) : 0); // SOUTH

			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}

		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + this.maxY, z + this.maxZ);
	}

	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityPipe();
	}
}

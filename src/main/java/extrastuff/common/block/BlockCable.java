package extrastuff.common.block;

import extrastuff.common.reference.Reference;
import extrastuff.common.tileentity.TileEntityCable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockCable extends BlockContainer {

	public BlockCable() {
		super(Material.ground);

		float pixel = 1F / 16F;
		this.setBlockBounds(5 * pixel, 5 * pixel, 5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockName(Reference.MOD_ID +":energyCable");
		this.useNeighborBrightness = true;
	}

	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		return getBounds(world, x, y, z);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return getBounds(world, x, y, z);
	}

	private AxisAlignedBB getBounds(World world, int x, int y, int z) {
		TileEntityCable cable = (TileEntityCable) world.getTileEntity(x, y, z);
		float pixel = 1F / 16F;
		// UP, DOWN, NORTH, EAST, SOUTH, WEST
		if(cable != null) {
			float minX = 5 * pixel - (cable.connections[5] != null ? (5 * pixel) : 0);		// WEST
			float maxX = 1 - 5 * pixel + (cable.connections[3] != null ? (5 * pixel) : 0);	// EAST
			float minY = 5 * pixel - (cable.connections[1] != null ? (5 * pixel) : 0); 		// DOWN
			float maxY = 1 - 5 * pixel + (cable.connections[0] != null ? (5 * pixel) : 0);	// UP
			float minZ = 5 * pixel - (cable.connections[2] != null ? (5 * pixel) : 0);		// NORTH
			float maxZ = 1 - 5 * pixel + (cable.connections[4] != null ? (5 * pixel) : 0);	// SOUTH

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
		return new TileEntityCable();
	}
}

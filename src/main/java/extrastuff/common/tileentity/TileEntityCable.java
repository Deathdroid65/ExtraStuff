package extrastuff.common.tileentity;

import extrastuff.common.ExtraStuff;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCable extends TileEntity {
	// UP, DOWN, NORTH, EAST, SOUTH, WEST
	public ForgeDirection[] connections = new ForgeDirection[6];
	public boolean shouldRenderCore = false;
	boolean upDown = false;
	boolean northSouth = false;
	boolean eastWest = false;

	public TileEntityCable() {

	}

	public void updateEntity() {
		// TODO should only update when PipeBlock is placed
		updateConnections();
		updateCore();
	}

	public void updateConnections() {
		// TODO Add checks for other blocks that pipes should connect with
		if(isCable(xCoord, yCoord + 1, zCoord)) {
			connections[0] = ForgeDirection.UP;
		} else {
			connections[0] = null;
		}

		if(isCable(xCoord, yCoord - 1, zCoord)) {
			connections[1] = ForgeDirection.DOWN;
		} else {
			connections[1] = null;
		}

		if(isCable(xCoord, yCoord, zCoord - 1)) {
			connections[2] = ForgeDirection.NORTH;
		} else {
			connections[2] = null;
		}

		if(isCable(xCoord + 1, yCoord, zCoord)) {
			connections[3] = ForgeDirection.EAST;
		} else {
			connections[3] = null;
		}

		if(isCable(xCoord, yCoord, zCoord + 1)) {
			connections[4] = ForgeDirection.SOUTH;
		} else {
			connections[4] = null;
		}

		if(isCable(xCoord - 1, yCoord, zCoord)) {
			connections[5] = ForgeDirection.WEST;
		} else {
			connections[5] = null;
		}
	}

	public boolean isCable(int x, int y, int z) {
		return (this.worldObj.getTileEntity(x, y, z) instanceof TileEntityCable);
	}

	public void updateCore() {
		int totalConnections = 0;

		for(int i = 0; i < connections.length; i++) {
			if (connections[i] != null) {
				totalConnections++;
			}
		}

		if(totalConnections == 2) {
			shouldRenderCore = false;
		} else {
			shouldRenderCore = true;
		}

		if(connections[0] != null && connections[1] != null) {
			upDown = true;
			northSouth = false;
			eastWest = false;
		} else if(connections[2] != null && connections[4] != null) {
			upDown = false;
			northSouth = true;
			eastWest = false;
		} else if(connections[3] != null && connections[5] != null) {
			upDown = false;
			northSouth = false;
			eastWest = true;
		} else {
			upDown = false;
			northSouth = false;
			eastWest = false;
			shouldRenderCore = true;
		}
	}

	public ForgeDirection getStraightDirection() {
		if(upDown) return ForgeDirection.UP;
		if(northSouth) return ForgeDirection.NORTH;
		if(eastWest) return ForgeDirection.EAST;
		ExtraStuff.logger.error("Unknown TileEntityCable direction!");
		return ForgeDirection.UNKNOWN;
	}
}

package extrastuff.client.render;

import extrastuff.common.reference.Reference;
import extrastuff.common.tileentity.TileEntityCable;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class TileEntityRenderCable extends TileEntitySpecialRenderer {
	ResourceLocation core = new ResourceLocation(Reference.MOD_ID, "textures/blocks/models/CableCore.png");
	ResourceLocation transmitter = new ResourceLocation(Reference.MOD_ID, "textures/blocks/models/CableTransmitter.png");

	float pixel = 1F / 16F; // Represents a single pixel in the texture
	float texturePixel = 1F / 24F;

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double translateX, double translateY, double translateZ, float f) {
		GL11.glTranslated(translateX, translateY, translateZ);
		GL11.glDisable(GL11.GL_LIGHTING);
		{
			TileEntityCable cable = (TileEntityCable) tileEntity;

			if(cable.shouldRenderCore) {
				drawCore();
				// Render connections
				for(int i = 0; i < cable.connections.length; i++) {
					if(cable.connections[i] != null) {
						drawConnection(cable.connections[i]);
					}
				}
			} else {
				// Render straight piece (UP, NORTH, EAST)
				drawStraight(cable.getStraightDirection());
			}
		}
		GL11.glEnable(GL11.GL_LIGHTING);
		// Translate (double) back
		GL11.glTranslated(-translateX, -translateY, -translateZ);
	}

	// TODO actually draw the straight section
	/**
	 * Draw straight pipe: UP, NORTH, EAST
	 */
	private void drawStraight(ForgeDirection direction) {
		// Translate to the center of the core
		this.bindTexture(transmitter);
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		if(direction == ForgeDirection.UP) {
			// ROTATE
		} else if(direction == ForgeDirection.NORTH) {
			GL11.glRotatef(270, 1, 0, 0);
		} else if(direction == ForgeDirection.EAST) {
			GL11.glRotatef(270, 0, 0, 1);
		}

		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		{
			// Front
			tessellator.addVertexWithUV(1 - 5 * pixel, 0, 1 - 5 * pixel, 0 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1, 1 - 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1, 1 - 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 0, 1 - 5 * pixel, 0 * texturePixel, 0 * texturePixel);

			// Back
			tessellator.addVertexWithUV(5 * pixel, 0, 5 * pixel, 0 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1, 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1, 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 0, 5 * pixel, 0 * texturePixel, 0 * texturePixel);

			// Side
			tessellator.addVertexWithUV(1 - 5 * pixel, 0, 5 * pixel, 0 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1, 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1, 1 - 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 0, 1 - 5 * pixel, 0 * texturePixel, 0 * texturePixel);

			// Side
			tessellator.addVertexWithUV(5 * pixel, 0, 1 - 5 * pixel, 0 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1, 1 - 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1, 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 0, 5 * pixel, 0 * texturePixel, 0 * texturePixel);
		}
		tessellator.draw();

		// Translate to the center of the core
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		if(direction == ForgeDirection.UP) {
			// ROTATE
		} else if(direction == ForgeDirection.NORTH) {
			GL11.glRotatef(-270, 1, 0, 0);
		} else if(direction == ForgeDirection.EAST) {
			GL11.glRotatef(-270, 0, 0, 1);
		}

		// Translate back to origin
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	private void drawConnection(ForgeDirection direction) {
		this.bindTexture(transmitter);
		// Translate to the center of the core
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		if(direction == ForgeDirection.UP) {
			// ROTATE
		} else if(direction == ForgeDirection.DOWN) {
			GL11.glRotatef(180, 1, 0, 0);
		} else if(direction == ForgeDirection.SOUTH) {
			GL11.glRotatef(90, 1, 0, 0);
		} else if(direction == ForgeDirection.NORTH) {
			GL11.glRotatef(270, 1, 0, 0);
		} else if(direction == ForgeDirection.EAST) {
			GL11.glRotatef(270, 0, 0, 1);
		} else if(direction == ForgeDirection.WEST) {
			GL11.glRotatef(90, 0, 0, 1);
		}

		// Translate back to origin
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		{
			// Front
			tessellator.addVertexWithUV(1 - 5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 0 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1, 1 - 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1, 1 - 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 0 * texturePixel, 0 * texturePixel);

			// Back
			tessellator.addVertexWithUV(5 * pixel, 1 - 5 * pixel, 5 * pixel, 0 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1, 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1, 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1 - 5 * pixel, 5 * pixel, 0 * texturePixel, 0 * texturePixel);

			// Side
			tessellator.addVertexWithUV(1 - 5 * pixel, 1 - 5 * pixel, 5 * pixel, 0 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1, 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1, 1 - 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 0 * texturePixel, 0 * texturePixel);

			// Side
			tessellator.addVertexWithUV(5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 0 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1, 1 - 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1, 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1 - 5 * pixel, 5 * pixel, 0 * texturePixel, 0 * texturePixel);
		}
		tessellator.draw();

		// Translate to the center of the core
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		if(direction == ForgeDirection.UP) {
			// ROTATE
		} else if(direction == ForgeDirection.DOWN) {
			GL11.glRotatef(-180, 1, 0, 0);
		} else if(direction == ForgeDirection.NORTH) {
			GL11.glRotatef(-270, 1, 0, 0);
		} else if(direction == ForgeDirection.SOUTH) {
			GL11.glRotatef(-90, 1, 0, 0);
		} else if(direction == ForgeDirection.EAST) {
			GL11.glRotatef(-270, 0, 0, 1);
		} else if(direction == ForgeDirection.WEST) {
			GL11.glRotatef(-90, 0, 0, 1);
		}

		// Translate back to origin
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}

	private void drawCore() {
		this.bindTexture(core);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		{
			// Draw counter-clockwise - bottom right, upper right, upper left, bottom left
			tessellator.addVertexWithUV(1 - 5 * pixel, 5 * pixel, 1 - 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 0 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 5 * pixel, 1 - 5 * pixel, 0 * texturePixel, 24 * texturePixel);

			tessellator.addVertexWithUV(1 - 5 * pixel, 5 * pixel, 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1 - 5 * pixel, 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 0 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 5 * pixel, 1 - 5 * pixel, 0 * texturePixel, 24 * texturePixel);

			// Mirror the first side, shift it over (remove '1 -'), rotate the texture
			tessellator.addVertexWithUV(5 * pixel, 5 * pixel, 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1 - 5 * pixel, 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1 - 5 * pixel, 5 * pixel, 0 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 5 * pixel, 5 * pixel, 0 * texturePixel, 24 * texturePixel);

			// Mirror the second side, shift it over (remove '1 -'), rotate the texture
			tessellator.addVertexWithUV(5 * pixel, 5 * pixel, 1 - 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1 - 5 * pixel, 5 * pixel, 0 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 5 * pixel, 5 * pixel, 0 * texturePixel, 24 * texturePixel);

			// Top side
			tessellator.addVertexWithUV(1 - 5 * pixel, 1 - 5 * pixel, 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1 - 5 * pixel, 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 0 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 1 - 5 * pixel, 1 - 5 * pixel, 0 * texturePixel, 24 * texturePixel);

			// Bottom side
			tessellator.addVertexWithUV(1 - 5 * pixel, 5 * pixel, 1 - 5 * pixel, 24 * texturePixel, 24 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 5 * pixel, 1 - 5 * pixel, 24 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(5 * pixel, 5 * pixel, 5 * pixel, 0 * texturePixel, 0 * texturePixel);
			tessellator.addVertexWithUV(1 - 5 * pixel, 5 * pixel, 5 * pixel, 0 * texturePixel, 24 * texturePixel);
		}
		tessellator.draw();
	}
}

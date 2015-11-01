package extrastuff.client.render;

import extrastuff.common.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemBlockEnergyCableRenderer implements IItemRenderer {
	ResourceLocation core = new ResourceLocation(Reference.MOD_ID, "textures/blocks/models/CableCore.png");

	float pixel = 1F / 16F;
	float texturePixel = 1F / 24F;

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type) {
			case ENTITY:
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON:
			case INVENTORY:
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch(type) {
			case ENTITY: {
				return (helper == ItemRendererHelper.ENTITY_BOBBING ||
						helper == ItemRendererHelper.ENTITY_ROTATION ||
						helper == ItemRendererHelper.BLOCK_3D);
			}
			case EQUIPPED: {
				return (helper == ItemRendererHelper.BLOCK_3D ||
						helper == ItemRendererHelper.EQUIPPED_BLOCK);
			}
			case EQUIPPED_FIRST_PERSON: {
				return (helper == ItemRendererHelper.EQUIPPED_BLOCK);
			}
			case INVENTORY: {
				return (helper == ItemRendererHelper.INVENTORY_BLOCK);
			}
			default: {
				return false;
			}
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Minecraft.getMinecraft().renderEngine.bindTexture(core);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();

		// adjust rendering space to match what caller expects
		boolean shouldUndoTranslate = false;
		switch (type) {
			case EQUIPPED:
			case EQUIPPED_FIRST_PERSON: {
				break; // caller expects us to render over [0,0,0] to [1,1,1], no translation necessary
			}
			case ENTITY:
			case INVENTORY: {
				// translate our coordinates so that [0,0,0] to [1,1,1] translates to the [-0.5, -0.5, -0.5] to [0.5, 0.5, 0.5] expected by the caller.
				GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
				shouldUndoTranslate = true;   // must undo the translation when we're finished rendering
				break;
			}
			default:
				break; // never here
		}

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

		if (shouldUndoTranslate) {
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}
	}
}

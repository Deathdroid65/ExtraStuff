package extrastuff.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class ExtraStuffRecipes {

	public static void register() {
		// 9 Titanium Ingots -> 1 Titanium Block
		GameRegistry.addRecipe(new ItemStack(ExtraStuffBlocks.titaniumBlock), new Object[]{
				"AAA",
				"AAA",
				"AAA",
				'A', ExtraStuffItems.titaniumIngot
		});

		// 1 Titanium Block -> 9 Titanium Ingots
		GameRegistry.addShapelessRecipe(new ItemStack(ExtraStuffItems.titaniumIngot, 9),  new Object[] {
				new ItemStack(ExtraStuffBlocks.titaniumBlock)
		});
	}
}

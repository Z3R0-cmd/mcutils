package me.Z3R0.mcutils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class ItemUtils {
	/*public static void updateTool(BlockPos pos) {
		Block block = Minecraft.getMinecraft().world.getBlockState(pos).getBlock();
		float strength = 1.0F;
		int bestItemIndex = -1;
		for (int i = 0; i < 9; i++) {
			ItemStack itemStack = Minecraft.getMinecraft().player.inventory.mainInventory.get(i).getItem();
			if (itemStack == null) {
				continue;
			}
			if ((itemStack.getStrVsBlock(block) > strength)) {
				strength = itemStack.getStrVsBlock(block);
				bestItemIndex = i;
			}
		}
		if (bestItemIndex != -1) {
			Minecraft.getMinecraft().player.inventory.currentItem = bestItemIndex;
		}
	}*/

}

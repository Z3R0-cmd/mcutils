package me.Z3R0.mcutils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class PlayerUtils {
	public static boolean isInLiquid() {
		if (Minecraft.getMinecraft().player.isInWater()) {
			return true;
		}
		boolean inLiquid = false;
		final int y = (int) Minecraft.getMinecraft().player.getEntityBoundingBox().minY;
		for (int x = MathHelper.floor(Minecraft.getMinecraft().player.getEntityBoundingBox().minX); x < MathHelper.floor(Minecraft.getMinecraft().player.getEntityBoundingBox().maxX) + 1; x++) {
			for (int z = MathHelper.floor(Minecraft.getMinecraft().player.getEntityBoundingBox().minZ); z < MathHelper.floor(Minecraft.getMinecraft().player.getEntityBoundingBox().maxZ) + 1; z++) {
				final Block block = Minecraft.getMinecraft().world.getBlockState(new BlockPos(x, y, z)).getBlock();
				if (block != null && !(block instanceof BlockAir)) {
					if (!(block instanceof BlockLiquid)) return false;
					inLiquid = true;
				}
			}
		}
		return inLiquid;
	}

}

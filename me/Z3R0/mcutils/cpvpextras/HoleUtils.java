package me.Z3R0.mcutils.cpvpextras;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class HoleUtils {
	public static boolean IsPlayerInHole()
    {
        BlockPos blockPos = new BlockPos(Math.floor(Minecraft.getMinecraft().player.posX), Math.floor(Minecraft.getMinecraft().player.posY), Math.floor(Minecraft.getMinecraft().player.posZ));
        
        IBlockState blockState = Minecraft.getMinecraft().world.getBlockState(blockPos);
        
        if (blockState.getBlock() != Blocks.AIR)
            return false;

        if (Minecraft.getMinecraft().world.getBlockState(blockPos.up()).getBlock() != Blocks.AIR)
            return false;

        if (Minecraft.getMinecraft().world.getBlockState(blockPos.down()).getBlock() == Blocks.AIR)
            return false;

        final BlockPos[] touchingBlocks = new BlockPos[]
        { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west() };

        int validHorizontalBlocks = 0;
        for (BlockPos touching : touchingBlocks)
        {
            final IBlockState touchingState = Minecraft.getMinecraft().world.getBlockState(touching);
            if ((touchingState.getBlock() != Blocks.AIR) && touchingState.isFullBlock())
                validHorizontalBlocks++;
        }

        if (validHorizontalBlocks < 4)
            return false;

        return true;
    }
}

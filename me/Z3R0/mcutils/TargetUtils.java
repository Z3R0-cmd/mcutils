package me.Z3R0.mcutils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class TargetUtils {
	public static EntityPlayer findClosestTarget() 
    {
    	if (Minecraft.getMinecraft().world.playerEntities.isEmpty())
    		return null;
    	
    	EntityPlayer closestTarget = null;
    	
        for (final EntityPlayer target : Minecraft.getMinecraft().world.playerEntities) 
        {
            if (target == Minecraft.getMinecraft().player)
                continue;
            
            
            //TODO: Make it not attack friends here
            
            if (!EntityUtils.isLiving((Entity)target))
                continue;
            
            if (target.getHealth() <= 0.0f)
                continue;

            if (closestTarget != null)
            	if (Minecraft.getMinecraft().player.getDistance(target) > Minecraft.getMinecraft().player.getDistance(closestTarget))
            		continue;

            closestTarget = target;
        }
        
        return closestTarget;
    }
}

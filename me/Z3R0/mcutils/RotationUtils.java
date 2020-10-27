package me.Z3R0.mcutils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class RotationUtils {
	
	protected static Minecraft mc = Minecraft.getMinecraft();
	
	/*public static float[] getRotationsNeeded(BlockPos pos) {
		double diffX = pos.getX() + 0.5 - mc.player.posX;
		double diffY = (pos.getY() + 0.5) - (mc.player.posY + mc.player.height);
		double diffZ = pos.getZ() + 0.5 - mc.player.posZ;
		double dist = MathHelper.sqrt_double((diffX * diffX) + (diffZ * diffZ));
		float yaw = (float) ((Math.atan2(diffZ, diffX) * 180.0D) / 3.141592653589793D) - 90.0F;
		float pitch = (float) -((Math.atan2(diffY, dist) * 180.0D) / 3.141592653589793D);
		return new float[] { mc.player.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - mc.player.rotationYaw), mc.player.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - mc.player.rotationPitch) };
	}*/
	
	
	
	public static float[] getRotationsNeeded(Entity entity) {
		if (entity == null) {
			return null;
		}
		double diffX = entity.posX - mc.player.posX;
		double diffZ = entity.posZ - mc.player.posZ;
		double diffY;
		if (entity instanceof EntityLivingBase) {
			final EntityLivingBase elb = (EntityLivingBase) entity;
			diffY = (elb.posY + elb.getEyeHeight()) - (mc.player.posY + mc.player.getEyeHeight());
		} else {
			diffY = ((entity.getEntityBoundingBox().minY + entity.getEntityBoundingBox().maxY) / 2.0D) - (mc.player.posY + mc.player.getEyeHeight());
		}
		double dist = MathHelper.sqrt((diffX * diffX) + (diffZ * diffZ));
		float yaw = (float) ((Math.atan2(diffZ, diffX) * 180.0D) / 3.141592653589793D) - 90.0F;
		float pitch = (float) -((Math.atan2(diffY, dist) * 180.0D) / 3.141592653589793D);
		return new float[] { mc.player.rotationYaw + MathHelper.wrapDegrees(yaw - mc.player.rotationYaw), mc.player.rotationPitch + MathHelper.wrapDegrees(pitch - mc.player.rotationPitch) };
	}
	
	
    public static float[] getBowAngles(final Entity entity) {
        final double xDelta = entity.posX - entity.lastTickPosX;
        final double zDelta = entity.posZ - entity.lastTickPosZ;
        double d = Minecraft.getMinecraft().player.getDistance(entity);
        d -= d % 0.8;
        double xMulti = 1.0;
        double zMulti = 1.0;
        final boolean sprint = entity.isSprinting();
        xMulti = d / 0.8 * xDelta * (sprint ? 1.25 : 1.0);
        zMulti = d / 0.8 * zDelta * (sprint ? 1.25 : 1.0);
        final double x = entity.posX + xMulti - Minecraft.getMinecraft().player.posX;
        final double z = entity.posZ + zMulti - Minecraft.getMinecraft().player.posZ;
        final double y = Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight() - (entity.posY + entity.getEyeHeight());
        final double dist = Minecraft.getMinecraft().player.getDistance(entity);
        final float yaw = (float) Math.toDegrees(Math.atan2(z, x)) - 90.0f;
        final float pitch = (float) Math.toDegrees(Math.atan2(y, dist));
        return new float[]{yaw, pitch};
    }



}

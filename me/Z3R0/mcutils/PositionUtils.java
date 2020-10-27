package me.Z3R0.mcutils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class PositionUtils {
    public float[] getRotationTo(final Vec3d pos) {
        final double xD = Minecraft.getMinecraft().player.posX - pos.x;
        final double yD = Minecraft.getMinecraft().player.posY + Minecraft.getMinecraft().player.getEyeHeight() - pos.y;
        final double zD = Minecraft.getMinecraft().player.posZ - pos.z;
        final double yaw = Math.atan2(zD, xD);
        final double pitch = Math.atan2(yD, Math.sqrt(Math.pow(xD, 2.0) + Math.pow(zD, 2.0)));
        return new float[] { (float)Math.toDegrees(yaw) + 90.0f, (float)Math.toDegrees(pitch) };
    }
    
    
    public static double GetDistance(double p_X, double p_Y, double p_Z, double x, double y, double z)
    {
        double d0 = p_X - x;
        double d1 = p_Y - y;
        double d2 = p_Z - z;
        return (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }
    
    
    public static Vec3d getInterpolatedAmount(Entity entity, double x, double y, double z)
    {
        return new Vec3d((entity.posX - entity.lastTickPosX) * x, 0 * y,
                (entity.posZ - entity.lastTickPosZ) * z);
    }

    public static Vec3d getInterpolatedAmount(Entity entity, Vec3d vec)
    {
        return getInterpolatedAmount(entity, vec.x, vec.y, vec.z);
    }

    public static Vec3d getInterpolatedAmount(Entity entity, double ticks)
    {
        return getInterpolatedAmount(entity, ticks, ticks, ticks);
    }

}

package me.Z3R0.mcutils;

import net.minecraft.client.Minecraft;

public class CameraUtils {
	private static final net.minecraft.client.Minecraft MC = net.minecraft.client.Minecraft.getMinecraft();

    private static float cameraYaw;
    private static float cameraPitch;
    private static boolean freeCameraSpectator;
    
    public static void setFreeCameraSpectator(boolean isSpectator)
    {
        freeCameraSpectator = isSpectator;
    }

    public static boolean getFreeCameraSpectator()
    {
        return freeCameraSpectator;
    }
    
    //public static boolean freecamEnabled()
    ////{
     //   return SummitStatic.FREECAM != null && SummitStatic.FREECAM.isEnabled() && SummitStatic.FREECAM.Mode.getValue().equals("Camera");
    //}
    
    
    
    public static boolean freecamEnabled() {
    	
    	// You need to make this get if freecam is on or not.
    	
    	return false;
    }

    public static boolean shouldPreventPlayerInputs()
    {
        return freecamEnabled();
    }

    public static boolean shouldPreventPlayerMovement()
    {
        return freecamEnabled();
    }

    public static float getCameraYaw()
    {
        return net.minecraft.util.math.MathHelper.wrapDegrees(cameraYaw);
    }

    public static float getCameraPitch()
    {
        return net.minecraft.util.math.MathHelper.wrapDegrees(cameraPitch);
    }

    public static void setCameraYaw(float yaw)
    {
        cameraYaw = yaw;
    }

    public static void setCameraPitch(float pitch)
    {
        cameraPitch = pitch;
    }

    public static void setCameraRotations(float yaw, float pitch)
    {
        CameraEntity camera = CameraEntity.getCamera();

        if (camera != null)
        {
            camera.setCameraRotations(yaw, pitch);
        }
    }

    public static void updateCameraRotations(float yawChange, float pitchChange)
    {
        CameraEntity camera = CameraEntity.getCamera();

        if (camera != null)
        {
            camera.updateCameraRotations(yawChange, pitchChange);
        }
    }

    public static void markChunksForRebuild(net.minecraft.client.renderer.ViewFrustum storage,
            int chunkX, int chunkZ, int lastChunkX, int lastChunkZ)
    {
        if (chunkX == lastChunkX && chunkZ == lastChunkZ)
        {
            return;
        }

        final int viewDistance = MC.gameSettings.renderDistanceChunks;
        net.minecraft.client.multiplayer.ChunkProviderClient provider = MC.world.getChunkProvider();

        if (chunkX != lastChunkX)
        {
            final int minCX = chunkX > lastChunkX ? lastChunkX + viewDistance : chunkX     - viewDistance;
            final int maxCX = chunkX > lastChunkX ? chunkX     + viewDistance : lastChunkX - viewDistance;

            for (int cx = minCX; cx <= maxCX; ++cx)
            {
                for (int cz = chunkZ - viewDistance; cz <= chunkZ + viewDistance; ++cz)
                {
                    if (provider.isChunkGeneratedAt(cx, cz))
                    {
                        int x = cx << 4;
                        int z = cz << 4;
                        storage.markBlocksForUpdate(x, 0, z, x, 255, z, false);
                    }
                }
            }
        }

        if (chunkZ != lastChunkZ)
        {
            final int minCZ = chunkZ > lastChunkZ ? lastChunkZ + viewDistance : chunkZ     - viewDistance;
            final int maxCZ = chunkZ > lastChunkZ ? chunkZ     + viewDistance : lastChunkZ - viewDistance;

            for (int cz = minCZ; cz <= maxCZ; ++cz)
            {
                for (int cx = chunkX - viewDistance; cx <= chunkX + viewDistance; ++cx)
                {
                    if (provider.isChunkGeneratedAt(cx, cz))
                    {
                        int x = cx << 4;
                        int z = cz << 4;
                        storage.markBlocksForUpdate(x, 0, z, x, 255, z, false);
                    }
                }
            }
        }
    }

    public static void markChunksForRebuildOnDeactivation(int lastChunkX, int lastChunkZ)
    {
        net.minecraft.client.multiplayer.ChunkProviderClient provider = MC.world.getChunkProvider();
        net.minecraft.entity.Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
        final int viewDistance = MC.gameSettings.renderDistanceChunks;
        final int chunkX = entity.chunkCoordX;
        final int chunkZ = entity.chunkCoordZ;

        final int minCameraCX = lastChunkX - viewDistance;
        final int maxCameraCX = lastChunkX + viewDistance;
        final int minCameraCZ = lastChunkZ - viewDistance;
        final int maxCameraCZ = lastChunkZ + viewDistance;
        final int minCX = chunkX - viewDistance;
        final int maxCX = chunkX + viewDistance;
        final int minCZ = chunkZ - viewDistance;
        final int maxCZ = chunkZ + viewDistance;

        for (int cz = minCZ; cz <= maxCZ; ++cz)
        {
            for (int cx = minCX; cx <= maxCX; ++cx)
            {
                // Mark all chunks that were not in free camera range
                if ((cx < minCameraCX || cx > maxCameraCX || cz < minCameraCZ || cz > maxCameraCZ) &&
                    provider.isChunkGeneratedAt(cx, cz))
                {
                    int x = cx << 4;
                    int z = cz << 4;
                    MC.world.markBlockRangeForRenderUpdate(x, 0, z, x, 255, z);
                }
            }
        }
    }
}

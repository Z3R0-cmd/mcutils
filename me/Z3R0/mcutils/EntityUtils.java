package me.Z3R0.mcutils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.*;


public class EntityUtils {
    public static boolean isPassive(Entity e)
    {
        if (e instanceof EntityWolf && ((EntityWolf) e).isAngry())
            return false;
        if (e instanceof EntityAnimal || e instanceof EntityAgeable || e instanceof EntityTameable
                || e instanceof EntityAmbientCreature || e instanceof EntitySquid)
            return true;
        if (e instanceof EntityIronGolem && ((EntityIronGolem) e).getRevengeTarget() == null)
            return true;
        return false;
    }
    
    
    public static boolean isLiving(Entity e)
    {
        return e instanceof EntityLivingBase;
    }
    
    public static boolean isFakeLocalPlayer(Entity entity)
    {
        return entity != null && entity.getEntityId() == -100 && Minecraft.getMinecraft().player != entity;
    }
    public static boolean isMobAggressive(Entity entity)
    {
        if (entity instanceof EntityPigZombie)
        {
            // arms raised = aggressive, angry = either game or we have set the anger
            // cooldown
            if (((EntityPigZombie) entity).isArmsRaised() || ((EntityPigZombie) entity).isAngry())
            {
                return true;
            }
        }
        else if (entity instanceof EntityWolf)
        {
            return ((EntityWolf) entity).isAngry()
                    && !Minecraft.getMinecraft().player.equals(((EntityWolf) entity).getOwner());
        }
        else if (entity instanceof EntityEnderman)
        {
            return ((EntityEnderman) entity).isScreaming();
        }
        return isHostileMob(entity);
    }
    
    public static boolean isNeutralMob(Entity entity)
    {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }
    
    
    public static boolean isFriendlyMob(Entity entity)
    {
        return (entity.isCreatureType(EnumCreatureType.CREATURE, false) && !isNeutralMob(entity))
                || (entity.isCreatureType(EnumCreatureType.AMBIENT, false)) || entity instanceof EntityVillager
                || entity instanceof EntityIronGolem || (isNeutralMob(entity) && !isMobAggressive(entity));
    }
    
    public static boolean isHostileMob(Entity entity)
    {
        return (entity.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(entity));
    }
    
    public static boolean isDrivenByPlayer(Entity entityIn)
    {
        return Minecraft.getMinecraft().player != null && entityIn != null
                && entityIn.equals(Minecraft.getMinecraft().player.getRidingEntity());
    }
    
}

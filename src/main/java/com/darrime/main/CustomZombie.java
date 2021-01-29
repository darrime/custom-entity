package com.darrime.main;

import net.minecraft.server.v1_16_R3.EntityZombie;
import net.minecraft.server.v1_16_R3.GenericAttributes;
import net.minecraft.server.v1_16_R3.World;
import org.bukkit.entity.Entity;
import java.lang.reflect.Field;
import java.util.Map;

public class CustomZombie extends EntityZombie {
    public CustomZombie(World world) {
        super(world);

        this.getAttributeInstance(GenericAttributes.ARMOR).setValue(100); //Max Health
        this.setHealth(100);


    }


    protected static Field mapStringToClassField, mapClassToStringField, mapClassToIdField, mapStringToIdField;

    static {
        try {
            mapStringToClassField = net.minecraft.server.v1_16_R3.EntityTypes.class.getDeclaredField("c");
            mapClassToStringField = net.minecraft.server.v1_16_R3.EntityTypes.class.getDeclaredField("d");

            mapClassToIdField = net.minecraft.server.v1_16_R3.EntityTypes.class.getDeclaredField("f");
            mapStringToIdField = net.minecraft.server.v1_16_R3.EntityTypes.class.getDeclaredField("g");

            mapStringToClassField.setAccessible(true);
            mapClassToStringField.setAccessible(true);
            //mapIdToClassField.setAccessible(true);
            mapClassToIdField.setAccessible(true);
            mapStringToIdField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected static void addCustomEntity(Class entityClass, String name, int id)
    {
        if (mapStringToClassField == null || mapStringToIdField == null || mapClassToStringField == null || mapClassToIdField == null)
        {
            return;
        }
        else
        {
            try
            {
                Map mapStringToClass = (Map) mapStringToClassField.get(null);
                Map mapStringToId = (Map) mapStringToIdField.get(null);
                Map mapClasstoString = (Map) mapClassToStringField.get(null);
                Map mapClassToId = (Map) mapClassToIdField.get(null);

                mapStringToClass.put(name, entityClass);
                mapStringToId.put(name, Integer.valueOf(id));
                mapClasstoString.put(entityClass, name);
                mapClassToId.put(entityClass, Integer.valueOf(id));

                mapStringToClassField.set(null, mapStringToClass);
                mapStringToIdField.set(null, mapStringToId);
                mapClassToStringField.set(null, mapClasstoString);
                mapClassToIdField.set(null, mapClassToId);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
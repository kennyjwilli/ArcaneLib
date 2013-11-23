
package net.arcanerealm.arcanelib;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 *
 * @author Kenny
 */
public class ArcaneTools 
{
    /**
     * Saves a Bukkit location
     * @param location Bukkit Location to be saved
     * @return Location in its save format
     */
    public static String saveLocation(Location location)
    {
        return location.getWorld().getName()+","+location.getBlockX()+","+location.getBlockY()+","+location.getBlockZ();
    }
    
    /**
     * Loads a location from its save format
     * @param save The string the location is saved in
     * @return Bukkit Location
     */
    public static Location getLocationFromSave(String save)
    {
        String[] split = save.split(",");
        String world = split[0];
        if(Bukkit.getWorld(world) == null)
        {
            return null;
        }
        int x = Integer.parseInt(split[1]);
        int y = Integer.parseInt(split[2]);
        int z = Integer.parseInt(split[3]);
        return new Location(Bukkit.getWorld(world), x, y, z);
    }
}


package net.arcanerealm.arcanelib;

import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
    
    /**
     * Prints a chat friendly version of a stacktrace
     * @param stackTrace The stack trace elements
     * @return Chat friendly version of a StackTrace
     */
    public static String getStackTraceError(StackTraceElement[] stackTrace)
    {
//        String fullClassName = stackTrace[stackTrace.length - 1].getClassName();
//        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
//        String methodName = stackTrace[stackTrace.length - 1].getMethodName();
//        int lineNumber = stackTrace[stackTrace.length - 1].getLineNumber();
        return stackTrace[stackTrace.length - 1].toString();
    }
    
    /**
     * Converts a list of strings into a single string separated by commas
     * @param list List of strings
     * @param color Should the method include default MC coloring?
     * @return List separated by commands of strings
     */
    public static String convertListToString(Collection<String> list, boolean color)
    {
        boolean first = false;
        int i = 1;
        StringBuilder result = new StringBuilder();
        for(String s : list)
        {
            if(first)
            {
                if(color)
                {
                    result.append(ChatColor.WHITE).append(", ");
                }else
                {
                    result.append(", ");
                }
            }else
            {
                first = true;
            }
            if(color)
            {
                if(i % 2 == 0)
                {
                    result.append(ChatColor.AQUA).append(s);
                }else
                {
                    result.append(ChatColor.GOLD).append(s);
                }
            }else
            {
                result.append(s);
            }
            i++;
        }
        return result.toString();
    }
}

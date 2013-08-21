
package net.arcanerealm.arcanelib.parse;

import info.jeppes.ZoneCore.ZoneConfig;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Kenny
 */
public class FindAndReplace
{
    private ZoneConfig config;
    private Plugin plugin;
    private boolean newFile;
    private HashMap<String, Object> replaceMap = new HashMap<>();
    
    public FindAndReplace(Plugin plugin, ZoneConfig config, boolean newFile)
    {
        this.plugin = plugin;
        this.config = config;
        this.newFile = newFile;
    }
    
    public void addReplace(String path, Object replaceWith)
    {
        replaceMap.put(path, replaceWith);
    }
    
    public void start()
    {
        ZoneConfig zConfig = checkNewFile();
        long tBefore = System.currentTimeMillis();
        for(Map.Entry kv : replaceMap.entrySet())
        {
            zConfig.set((String) kv.getKey(), kv.getValue());
        }
        zConfig.save();
        long duration = System.currentTimeMillis() - tBefore;
        double durationOut = duration / 1000;
        Bukkit.getLogger().log(Level.INFO, "Replacement complete! ("+durationOut+" seconds)");
    }
    
    private ZoneConfig checkNewFile()
    {
        ZoneConfig zConfig;
        File file = null;
        
        if(!newFile)
        {
            zConfig = config;
        }else
        {
            boolean found = false;
            int i = 1;
            while(!found)
            {
                file = new File(config.getCurrentPath()+config.getFileName()+"_"+i);
                if(!file.exists())
                {
                    found = true;
                }
                i++;
            }
            zConfig = new ZoneConfig(plugin, file);
        }
        return zConfig;
    }
}

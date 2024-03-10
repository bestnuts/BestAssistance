package org.nuts.bestassistance.Data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {
    private final File file = new File("plugins/BestAssistant/config.yml");
    public final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    private static Config INSTANCE;

    private Config(){}

    public static Config getINSTANCE(){
        if (INSTANCE==null){
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

    public void loadConfig(){
        try {
            if (!file.exists()) {
                config.set("Motd.Line-1", "TEST");
                config.set("Motd.Line-2", "TEST");
                config.set("Server.State", "work");
                config.set("Discord.token", "none");
                config.set("Discord.guild", "none");
                config.set("Discord.chat", "none");
                saveConfig();
            }
            config.load(file);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    public void saveConfig(){
        try {
            config.save(file);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}

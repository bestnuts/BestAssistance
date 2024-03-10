package org.nuts.bestassistance;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.nuts.bestassistance.Data.Config;
import org.nuts.bestassistance.Discord.BotConnect;
import org.nuts.bestassistance.Util.ChatFormat;

public final class BestAssistance extends JavaPlugin implements Listener {

    private final Config instanceConfig = Config.getINSTANCE();

    @Override
    public void onEnable() {
        instanceConfig.loadConfig();

        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Player(), this);
        BotConnect.onEnable();
    }

    @Override
    public void onDisable() {
        BotConnect.onDisable();
    }

    @EventHandler
    private void updateServerList(ServerListPingEvent e){
        String motd_1 = instanceConfig.config.getString("Motd.Line-1");
        String motd_2 = instanceConfig.config.getString("Motd.Line-2");
        String motd = motd_1 + "\n" + motd_2;
        e.setMotd(ChatFormat.colorFormat(motd));
    }
}

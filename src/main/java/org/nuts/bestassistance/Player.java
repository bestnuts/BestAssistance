package org.nuts.bestassistance;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.nuts.bestassistance.Discord.serverInfoConnection;

public class Player implements Listener {
    private final String join = "§7[§a+§7] §f";
    private final String leave = "§7[§c-§7] §f";

    @EventHandler
    private void joinEvent(PlayerJoinEvent e){
        e.setJoinMessage(join + e.getPlayer().getName());
        serverInfoConnection.updateBot();
    }

    @EventHandler
    private void leaveEvent(PlayerQuitEvent e){
        e.setQuitMessage(leave + e.getPlayer().getName());
    }
}

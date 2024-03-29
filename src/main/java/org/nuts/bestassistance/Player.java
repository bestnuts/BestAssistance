package org.nuts.bestassistance;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.scoreboard.*;
import org.nuts.bestassistance.Discord.serverInfoConnection;

public class Player implements Listener {
    private final String join = "§7[§a+§7] §f";
    private final String leave = "§7[§c-§7] §f";

    private final ScoreboardManager manager = Bukkit.getScoreboardManager();
    private final Scoreboard board = manager.getMainScoreboard();

    @EventHandler
    private void joinEvent(PlayerJoinEvent e){
        String name = e.getPlayer().getName();
        serverInfoConnection.chatToChannel("", name, "join");
        e.setJoinMessage(join + name);
        serverInfoConnection.updateBot();
        org.bukkit.entity.Player p = e.getPlayer();
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam("SOLO");
        team.addPlayer(p);
    }

    @EventHandler
    private void leaveEvent(PlayerQuitEvent e){
        String name = e.getPlayer().getName();
        serverInfoConnection.chatToChannel("", name, "quit");
        e.setQuitMessage(leave + name);
        serverInfoConnection.updateBot();
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        org.bukkit.entity.Player p = e.getPlayer();
        Action a = e.getAction();
        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
        }
        if (a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK) {
            Objective obj1 = board.getObjective("click.left");
            Score s = obj1.getScore(p);
            s.setScore(1);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        org.bukkit.entity.Player p = e.getPlayer();
        if(e.getItemDrop().getItemStack().getType() == Material.CARROT_ON_A_STICK) {
            Objective obj1 = board.getObjective("Item.carrot_drop");
            Score s = obj1.getScore(p);
            s.setScore(1);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        String originalChat = e.getMessage();
        org.bukkit.entity.Player p = e.getPlayer();
        serverInfoConnection.chatToChannel(originalChat, p.getName(), "chat");
    }
}

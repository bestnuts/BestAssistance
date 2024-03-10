package org.nuts.bestassistance.Discord;

import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;

import java.util.Timer;
import java.util.TimerTask;

public class serverInfoConnection {
    public static void updateBot(){
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                int currentPlayerCount = Bukkit.getServer().getOnlinePlayers().size();
                String activity = currentPlayerCount+"명이 접속";
                BotConnect.jda.getPresence().setActivity(Activity.playing(activity));
            }
        };
        timer.schedule(timerTask, 51);
    }
}

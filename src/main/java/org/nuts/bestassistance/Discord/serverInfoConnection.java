package org.nuts.bestassistance.Discord;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.nuts.bestassistance.Data.Config;
import org.nuts.bestassistance.Util.ChatFormat;

import java.util.*;

public class serverInfoConnection{
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
    public static void chatToChannel(String chat, String name, String type){
        Config instanceConfig = Config.getINSTANCE();
        Guild guild = BotConnect.jda.getGuildById(instanceConfig.config.getString("Discord.guild"));
        if(guild != null){
            String stringChannelID = instanceConfig.config.getString("Discord.chat");
            if(!stringChannelID.equals("none")){
                TextChannel channel = guild.getTextChannelById(stringChannelID);
                if(channel != null){
                    switch (type){
                        case "chat":
                            channel.sendMessage("**[" + ChatFormat.getDate() + "]** " + "`" + name + ":` " + chat).queue();
                            break;
                        case "join":
                            channel.sendMessage("**[" + ChatFormat.getDate() + "]** " + "`" + name + "님이 접속하셨습니다.`").queue();
                            break;
                        case "quit":
                            channel.sendMessage("**[" + ChatFormat.getDate() + "]** " + "`" + name + "님이 퇴장하셨습니다.`").queue();
                            break;
                    }
                }
            }
        }
    }
}

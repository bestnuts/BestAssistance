package org.nuts.bestassistance.Discord;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.nuts.bestassistance.Data.Config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

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
    public static void chatToChannel(String chat, String name){
        Config instanceConfig = Config.getINSTANCE();
        Guild guild = BotConnect.jda.getGuildById(instanceConfig.config.getString("Discord.guild"));
        if(guild != null){
            String stringChannelID = instanceConfig.config.getString("Discord.chat");
            if(!stringChannelID.equals("none")){
                TextChannel channel = guild.getTextChannelById(stringChannelID);
                if(channel != null){
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy. MM. dd. a hh:mm", Locale.KOREA);
                    channel.sendMessage("**[" + dateFormat.format(date) + "]** " + "`" + name + ":` " + chat).queue();
                }
            }
        }
    }
}

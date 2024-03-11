package org.nuts.bestassistance.Discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nuts.bestassistance.Data.Config;
import org.nuts.bestassistance.Util.ChatFormat;

import java.awt.*;
import java.util.*;
import java.util.List;

public class serverInfoCommand extends ListenerAdapter {
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e){
        String cmd = e.getName();
        e.deferReply().queue();
        EmbedBuilder embed = new EmbedBuilder();
        switch (cmd) {
            case "인원":
                embed.setTitle("BestCompany");
                List<String> playerList = new ArrayList<>();
                for(Player p : Bukkit.getOnlinePlayers()){
                    playerList.add(p.getName());
                }
                embed.setDescription(playerList.toString());
                embed.setFooter(ChatFormat.getDate());
                embed.setColor(new Color(255, 255, 0));
                e.getHook().sendMessageEmbeds(embed.build()).setEphemeral(true).queue();
                break;
        }
    }
    public void onGuildReady(@NotNull GuildReadyEvent e){
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("인원","서버 인원을 확인합니다."));
        e.getGuild().updateCommands().addCommands(commandData).queue();
    }
    public void onMessageReceived(@NotNull MessageReceivedEvent e){
        if(!e.getAuthor().isBot()){
            Config instanceConfig = Config.getINSTANCE();
            String stringChannelID = instanceConfig.config.getString("Discord.chat");
            TextChannel channel = e.getChannel().asTextChannel();
            if(channel.getId().equals(stringChannelID)){
                String chat = e.getMessage().getContentRaw();
                String nick = e.getMember().getUser().getName();
                String outputText = "[" + nick + "] " + chat;
                Bukkit.getConsoleSender().sendMessage(outputText);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage(outputText);
                }
            }
        }
    }
}

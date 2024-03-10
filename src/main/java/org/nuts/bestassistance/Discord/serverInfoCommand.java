package org.nuts.bestassistance.Discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class serverInfoCommand extends ListenerAdapter implements Listener {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent e){
        String cmd = e.getName();
        e.deferReply().queue();
        EmbedBuilder embed = new EmbedBuilder();
        switch (cmd) {
            case "인원":
                DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy '@' HH:mm:ss");
                String date = dateFormat.format(Calendar.getInstance().getTime());

                embed.setTitle("BestCompany");
                List<String> playerList = new ArrayList<>();
                for(Player p : Bukkit.getOnlinePlayers()){
                    playerList.add(p.getName());
                }
                embed.setDescription(playerList.toString());
                embed.setFooter(date);
                embed.setColor(new Color(255, 255, 0));
                e.getHook().sendMessageEmbeds(embed.build()).setEphemeral(true).queue();
                break;
        }
    }
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent e){
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("인원","서버 인원을 확인합니다."));
        e.getGuild().updateCommands().addCommands(commandData).queue();
    }
}

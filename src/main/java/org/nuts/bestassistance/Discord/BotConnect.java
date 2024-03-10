package org.nuts.bestassistance.Discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.nuts.bestassistance.Data.Config;

public class BotConnect {

    public static JDA jda;

    public static void onEnable(){
        Config instanceConfig = Config.getINSTANCE();
        String BotToken = instanceConfig.config.getString("Discord.token");
        if(!BotToken.equals("none")){
            jda = JDABuilder.createDefault(BotToken).setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.GUILD_PRESENCES,GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MEMBERS,GatewayIntent.MESSAGE_CONTENT, GatewayIntent.DIRECT_MESSAGES).setAutoReconnect(true).build();
            jda.addEventListener(new serverInfoConnection());
        }
    }

    public static void onDisable(){
        jda.shutdownNow();
    }

}

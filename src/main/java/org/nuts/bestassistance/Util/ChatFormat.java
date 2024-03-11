package org.nuts.bestassistance.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ChatFormat {
    public static String colorFormat(String string){
        string = string.replace("&0", "§0");
        string = string.replace("&1", "§1");
        string = string.replace("&2", "§2");
        string = string.replace("&3", "§3");
        string = string.replace("&4", "§4");
        string = string.replace("&5", "§5");
        string = string.replace("&6", "§6");
        string = string.replace("&7", "§7");
        string = string.replace("&8", "§8");
        string = string.replace("&9", "§9");
        string = string.replace("&a", "§a");
        string = string.replace("&b", "§b");
        string = string.replace("&c", "§c");
        string = string.replace("&d", "§d");
        string = string.replace("&e", "§e");
        string = string.replace("&f", "§f");
        string = string.replace("&k", "§k");
        string = string.replace("&l", "§l");
        string = string.replace("&m", "§m");
        string = string.replace("&n", "§n");
        string = string.replace("&o", "§o");
        string = string.replace("&r", "§r");
        return string;
    }
    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. a hh:mm", Locale.KOREA);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateResult = sdf.format(date);
        return dateResult;
    }
}

package me.crimson.transitcore;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by CrimsonCode on 11/11/2015.
 */
public class Listeners implements Listener {

    @EventHandler
    public void onChatFormat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        //Donor ranks
        if (player.hasPermission("vip.chat")) {
            event.setFormat(ChatColor.GREEN + "VIP" + ChatColor.GRAY + "%s" + " \u00BB " + "%s");

        } else if (player.hasPermission("vip.plus.chat")) {
            event.setFormat(ChatColor.AQUA + "VIP+" + ChatColor.GRAY + "%s" + " \u00BB " + "%s");



        //Staff ranks
        } else if (player.hasPermission("jrmod.chat")) {
            event.setFormat(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "JR MOD" + ChatColor.GRAY + "%s" + " \u00BB " + "%s");


        } else if (player.hasPermission("mod.chat")) {
            event.setFormat(ChatColor.RED + "" + ChatColor.BOLD + "MOD" + ChatColor.GRAY + "%s" + " \u00BB " + "%s");


        } else if (player.hasPermission("srmod.chat")) {
            event.setFormat(ChatColor.DARK_RED + "" + ChatColor.BOLD + "SR MOD" + ChatColor.GRAY + "%s" + " \u00BB " + "%s");


        } else if (player.hasPermission("dev.chat")) {
            event.setFormat(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "DEVELOPER" + ChatColor.GRAY + "%s" + " \u00BB " + "%s");


        } else if (player.hasPermission("manager.chat")) {
            event.setFormat(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "MANAGER" + ChatColor.GRAY + "%s" + " \u00BB " + "%s");


        } else if (player.hasPermission("admin.chat")) {
            event.setFormat(ChatColor.GOLD + "" + ChatColor.BOLD + "ADMIN" + ChatColor.GRAY + "%s" + " \u00BB " + "%s");


        } else {
            event.setFormat(ChatColor.GRAY + "%s" + " \u00BB " + "%s");
        }
    }


}

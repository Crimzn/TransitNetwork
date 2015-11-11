package me.crimson.transitcore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;

/**
 * Created by CrimsonCode on 11/11/2015.
 */
public class TransitCore extends JavaPlugin {

    //Report Arraylists
    ArrayList<String> reports = new ArrayList<String>();

    public void onEnable() {

        getLogger().info("Staff Commands has successfully been activated.");
        getServer().getPluginManager().registerEvents(new Listeners(), this);

    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = (Player) sender;

        //Report system
        if (cmd.getName().equalsIgnoreCase("report")) {
            if (player instanceof Player) {
                if (player.hasPermission("report.send")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.RED + "Error: Not enough arguments!");
                        return true;
                    }

                    //string building
                    StringBuilder str = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        str.append(args[i] + " ");
                    }
                    String reportReason = str.toString();

                    final Player reported = Bukkit.getServer().getPlayer(args[0]);
                    final String server = Bukkit.getServerName();

                    if (reported == null) {
                        player.sendMessage(ChatColor.RED + "Player not online!");
                        return true;
                    }
                    if (args.length == 1) {
                        player.sendMessage(ChatColor.RED + "Error: Not enough arguments");
                        return true;
                    }

                    if (args.length >= 2) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("report.see") || p.isOp()) {
                                p.sendMessage(ChatColor.DARK_RED + "[Report]" + ChatColor.GOLD + server + ChatColor.AQUA + player.getName() + ChatColor.YELLOW + " has reported " + ChatColor.AQUA + reported.getName() + ChatColor.WHITE + ": " + reportReason);
                            }
                            sender.sendMessage(ChatColor.GREEN + "Report sent to all online staff successfully!");
                            reports.add(ChatColor.AQUA + reported.getName() + ChatColor.WHITE + ": " + reportReason);
                            return true;
                        }
                    }

                }
            }
        }
        //TODO: Create a report list that shows all recent reports.


        //Warn
        if (cmd.getName().equalsIgnoreCase("warn")) {
            if (sender.hasPermission("punishment.warn")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Error: Correct usage - /warn <player> <reason>");
                    return true;
                }

                StringBuilder str = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    str.append(args[i] + " ");
                }
                String warnMessage = str.toString();

                final Player target = Bukkit.getServer().getPlayerExact(args[0]);

                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Error: Player, " + target.getDisplayName() + ", is not online!");
                    return true;
                }
                if (args.length == 1) {
                    player.sendMessage(ChatColor.RED + "Error: Please specify a reason!");
                    return true;
                }
                if (args.length >= 2) {
                    target.sendMessage(ChatColor.DARK_RED + "[WARN] " + ChatColor.GOLD + "" + ChatColor.BOLD + player.getName() + " - " + ChatColor.AQUA + "" + ChatColor.ITALIC + warnMessage);
                    getServer().broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.AQUA + "\u00BB "
                            + ChatColor.DARK_RED + "" + ChatColor.UNDERLINE + "Warned" +
                            ChatColor.AQUA + "\u00BB" + ChatColor.GOLD + "" + ChatColor.BOLD + target.getName() +
                            ChatColor.AQUA + " \u00BB " + ChatColor.GOLD + "" + ChatColor.ITALIC + warnMessage);
                    return true;

                }
            }
        }


        //Kick
        if (cmd.getName().equalsIgnoreCase("kick")) {
            if (sender.hasPermission("punishment.kick")) {
                if (player instanceof Player) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.RED + "Error: Correct usage - /kick <player> <reason>");
                        return true;
                    }

                    StringBuilder str = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        str.append(args[i] + " ");
                    }
                    String kickMessage = str.toString();

                    final Player target = Bukkit.getServer().getPlayerExact(args[0]);

                    if (target == null) {
                        player.sendMessage(ChatColor.RED + "Error: Player, " + target.getDisplayName() + ", cannot be found!");
                        return true;
                    }

                    if (args.length == 1) {
                        player.sendMessage(ChatColor.RED + "Error: Please specify a reason!");
                        return true;
                    }
                    //TODO: Improve kick message
                    if (args.length >= 2) {
                        target.kickPlayer(ChatColor.DARK_RED + "[KICK] " + ChatColor.GOLD + "" + ChatColor.BOLD + player.getName() + "\n " + ChatColor.AQUA + "" + ChatColor.ITALIC + kickMessage + "\n" + "\n" + ChatColor.RESET + "If this is an error, please contact support@transitnetwork.ga");
                        getServer().broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.AQUA + " \u00BB "
                                + ChatColor.DARK_RED + "" + ChatColor.UNDERLINE + "Kicked" +
                                ChatColor.AQUA + " \u00BB " + ChatColor.GOLD + "" + ChatColor.BOLD + target.getName() +
                                ChatColor.AQUA + " \u00BB " + ChatColor.GOLD + "" + ChatColor.ITALIC + kickMessage);
                    }
                }

            }
        }


        //BAN
        if (cmd.getName().equalsIgnoreCase("ban")) {
            if (player.hasPermission("punishment.ban")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Error: Correct usage - /ban <player> <reason>");
                    return true;
                }

                StringBuilder str = new StringBuilder();
                for (int i = 1; i < args.length; i++) {

                }
                String banMessage = str.toString();

                final Player target = Bukkit.getServer().getPlayerExact(args[0]);

                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Error: player, " + target.getDisplayName() + ", is not online.");
                    return true;
                }

                if (args.length == 1) {
                    player.sendMessage(ChatColor.RED + "Error: Please specify a reason!");
                    return true;
                }

                if (args.length >= 2) {
                    target.kickPlayer(ChatColor.DARK_RED + "[BAN] " + ChatColor.GOLD + "" + ChatColor.BOLD + player.getName() + "\n " + ChatColor.AQUA + "" + ChatColor.ITALIC + banMessage + "\n" + "\n" + ChatColor.RESET + "If this is an error, please contact support@transitnetwork.ga");
                    target.setBanned(true);
                    getServer().broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.AQUA + " \u00BB "
                            + ChatColor.DARK_RED + "" + ChatColor.UNDERLINE + "Permanent Ban" +
                            ChatColor.AQUA + " \u00BB " + ChatColor.GOLD + "" + ChatColor.BOLD + target.getName() +
                            ChatColor.AQUA + " \u00BB " + ChatColor.GOLD + "" + ChatColor.ITALIC + banMessage);
                }
            }
        }


        //MODERATOR CHAT
        if (cmd.getName().equalsIgnoreCase("s")) {
            if (sender instanceof Player) {
                Player m = (Player) sender;
                if (m.hasPermission("staffchat.mod")) {
                    if (args.length == 0) {
                        m.sendMessage(ChatColor.RED + "Correct usage: /s [message]");
                        return true;
                    } else {
                        String msg = "";
                        for (int i = 0; i < args.length; i++) {
                            msg = msg + args[i] + ' ';
                        }

                        String path = ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.RED + "" + ChatColor.BOLD + "{TYPE}" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET + "{NAME}" + ChatColor.WHITE + " \u00BB {MESSAGE}";
                        path = path.replace("{TYPE}", "S");
                        path = path.replace("{NAME}", m.getDisplayName());
                        path = path.replace("{MESSAGE}", msg);

                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("staffchat.mod.see")) p.sendMessage(path);
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You have no permission!");
                    return true;
                }
            }

        }


        //ADMIN CHAT
        if (cmd.getName().equalsIgnoreCase("a")) {
            if (sender instanceof Player) {
                Player m = (Player) sender;
                if (m.hasPermission("staffchat.admin")) {
                    if (args.length == 0) {
                        m.sendMessage(ChatColor.RED + "Correct usage: /a [message]");
                        return true;
                    } else {
                        String msg = "";
                        for (int i = 0; i < args.length; i++) {
                            msg = msg + args[i] + ' ';
                        }

                        String path = ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.GOLD + "" + ChatColor.BOLD + "{TYPE}" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET + "{NAME}" + ChatColor.WHITE + " \u00BB {MESSAGE}";
                        path = path.replace("{TYPE}", "A");
                        path = path.replace("{NAME}", m.getDisplayName());
                        path = path.replace("{MESSAGE}", msg);

                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("staffchat.admin.see")) p.sendMessage(path);
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You have no permission!");
                    return true;
                }
            }
        }


        //DEVELOPER CHAT
        if (cmd.getName().equalsIgnoreCase("d")) {
            if (sender instanceof Player) {
                Player m = (Player) sender;
                if (m.hasPermission("staffchat.dev")) {
                    if (args.length == 0) {
                        m.sendMessage(ChatColor.RED + "Correct usage: /d [message]");
                        return true;
                    } else {
                        String msg = "";
                        for (int i = 0; i < args.length; i++) {
                            msg = msg + args[i] + ' ';
                        }

                        String path = ChatColor.GRAY + "" + ChatColor.BOLD + "[" + ChatColor.BLUE + "" + ChatColor.BOLD + "{TYPE}" + ChatColor.GRAY + "" + ChatColor.BOLD + "] " + ChatColor.RESET + "{NAME}" + ChatColor.WHITE + " \u00BB {MESSAGE}";
                        path = path.replace("{TYPE}", "D");
                        path = path.replace("{NAME}", m.getDisplayName());
                        path = path.replace("{MESSAGE}", msg);

                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.hasPermission("staffchat.dev.see")) p.sendMessage(path);
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "You have no permission!");
                    return true;
                }
            }
        }


        //clear chat
        if (cmd.getName().equalsIgnoreCase("cc")) {
            Player staff = (Player) sender;
            if (staff.hasPermission("chat.moderation.cc")) {
                if (args.length > 0) {
                    staff.sendMessage(ChatColor.RED + "Correct usage: /cc ");
                    return true;
                }
                for (int i = 0; i < 100; i++) {
                    Bukkit.broadcastMessage("");
                }

            }
        }

        //Rank manager: (/rank)
        if(cmd.getName().equalsIgnoreCase("rank")){
            if(args.length < 2){
                player.sendMessage(ChatColor.RED + "No");
            }
            if(args.length == 3){
                Player tplayer = player.getServer().getPlayer(args[0]);
                String set_remove = args[1];
                String group = args[2];

                if(set_remove.contains("+")) {
                    PermissionGroup[] groups = {PermissionsEx.getPermissionManager().getGroup(group)};
                    PermissionsEx.getUser(tplayer).setGroups(groups);
                    player.sendMessage(ChatColor.GREEN + "Ranks updated.");
                } else if(set_remove.contains("-")){
                    PermissionGroup[] groups = {PermissionsEx.getPermissionManager().getGroup(group)};
                    PermissionsEx.getUser(tplayer).removeGroup(group);
                    player.sendMessage(ChatColor.GREEN + "Rank updated.");
                }

            }
        }


        //player's information
        if (cmd.getName().equalsIgnoreCase("info")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("info.see")) {
                    if (args.length == 0) {
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "TRANSIT > " + ChatColor.RED + "/info <player>");

                    } else {
                        final Player target = Bukkit.getServer().getPlayer(args[0]);
                        sender.sendMessage(ChatColor.GOLD + target.getDisplayName() + "'s IP: " + target.getAddress());
                    }
                }
            }
        }


        //TODO: Create muting/unmuting & toggable chat features (for moderation)
        return true;
    }


}


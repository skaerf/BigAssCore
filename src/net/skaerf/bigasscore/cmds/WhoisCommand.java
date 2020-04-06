package net.skaerf.bigasscore.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.skaerf.bigasscore.ConfigManager;
import net.skaerf.bigasscore.Events;

public class WhoisCommand implements CommandExecutor {
	
	ConfigManager CFGm = new ConfigManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.GREEN+"Usage: /whois <player>");
			}
			else {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(ChatColor.RED+"That player is not online!");
				}
				else {
					FileConfiguration targetFile = CFGm.getPlayerFile(target.getUniqueId().toString());
					sender.sendMessage(Events.translate("&a&lNickname&6: "+targetFile.getString("nickname")));
					sender.sendMessage(Events.translate("&a&lUUID&6: "+targetFile.getString("uuid")));
					sender.sendMessage(Events.translate("&a&lHealth&6: "+target.getHealth()));
					sender.sendMessage(Events.translate("&a&lHunger&6: "+target.getFoodLevel()));
					sender.sendMessage(Events.translate("&a&lEXP&6: "+target.getExpToLevel()));
					sender.sendMessage(Events.translate("&a&lLocation&6: "+target.getLocation().getWorld().getName()+", "+target.getLocation().getX()+", "+target.getLocation().getY()+", "+target.getLocation().getZ()));
					sender.sendMessage(Events.translate("&a&lBalance&6: Coming Soon"));
					sender.sendMessage(Events.translate("&a&lGamemode&6: "+target.getGameMode()));
					sender.sendMessage(Events.translate("&a&lCurr. IP&6: "+target.getAddress().toString()));
					sender.sendMessage(Events.translate("&a&lPast IPs&6: "+targetFile.getStringList("ips")));
					
				}
			}
		}
		else {
			sender.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6Only console can perform this command!"));
		}
		return true;
	}

}

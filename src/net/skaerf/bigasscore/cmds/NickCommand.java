package net.skaerf.bigasscore.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.skaerf.bigasscore.ConfigManager;
import net.skaerf.bigasscore.Events;
import net.skaerf.bigasscore.Permissions;

public class NickCommand implements CommandExecutor {
	
	private ConfigManager CFGm = new ConfigManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Permissions.NO_CONSOLE);
		}
		else {
			Player player = (Player) sender;
			if (player.hasPermission(Permissions.CMD_NICK)) {
				if (args.length == 0) {
					player.sendMessage(ChatColor.RED+"Usage: /nick <name/off>");
				}
				else if (args[0].equalsIgnoreCase("off")) {
					CFGm.getPlayerFile(player.getUniqueId().toString()).set("nickname", player.getName());
					CFGm.savePlayerFile(player.getUniqueId().toString());
					player.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6Your nickname was turned off!"));
				}
				else {
					FileConfiguration file = CFGm.getPlayerFile(player.getUniqueId().toString());
					file.set("nickname", args[0]);
					CFGm.savePlayerFile(player.getUniqueId().toString());
					player.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6Your nickname was set to "+args[0]+"!"));
				}
			}
			else {
				player.sendMessage(Permissions.NO_PERM);
			}
		}
		return true;
	}

}

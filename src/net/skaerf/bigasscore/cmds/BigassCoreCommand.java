package net.skaerf.bigasscore.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.bigasscore.ConfigManager;
import net.skaerf.bigasscore.Events;
import net.skaerf.bigasscore.Permissions;

public class BigassCoreCommand implements CommandExecutor {
	
	ConfigManager CFGm = new ConfigManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage("/bigasscore <reload>");
			}
			else {
				if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
					CFGm.reloadAll();
				}
			}
		}
		else {
			Player player = (Player) sender;
			if (player.hasPermission(Permissions.BIG_ASS_CORE)) {
				if (args.length == 0) {
					sender.sendMessage("/bigasscore <reload>");
				}
				else {
					if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
						CFGm.reloadAll();
						sender.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6All main configs were reloaded!"));
					}
				}
			}
			else {
				player.sendMessage(Permissions.NO_PERM);
			}
		}
		return true;
	}

}

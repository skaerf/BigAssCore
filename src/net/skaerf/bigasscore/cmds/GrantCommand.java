package net.skaerf.bigasscore.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.bigasscore.ConfigManager;
import net.skaerf.bigasscore.Permissions;
import net.skaerf.bigasscore.guis.GrantGUIs;

public class GrantCommand implements CommandExecutor {
	
	ConfigManager CFGm = new ConfigManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Permissions.NO_CONSOLE);
		}
		else {
			Player player = (Player) sender;
			if (player.hasPermission(Permissions.CMD_GRANT)) {
				if (args.length == 0) {
					GrantGUIs.selUserGrantGUI(player);
				}
			}
		}
		return true;
	}

}

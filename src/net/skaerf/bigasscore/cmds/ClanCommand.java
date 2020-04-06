package net.skaerf.bigasscore.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.bigasscore.Permissions;

public class ClanCommand implements CommandExecutor {
	
	@Override 
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Permissions.NO_CONSOLE);
		}
		else {
			Player player = (Player) sender;
			if (args.length == 0) {
				player.sendMessage("");
			}
		}
		return true;
	}

}

package net.skaerf.bigasscore.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.bigasscore.Events;
import net.skaerf.bigasscore.Permissions;

public class FlyCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Permissions.NO_CONSOLE);
			sender.sendMessage("Console can't fly, stupid!");
		}
		else {
			Player player = (Player) sender;
			if (player.getLocation().getWorld().getName().equalsIgnoreCase("skyblock") || player.getLocation().getWorld().getName().equalsIgnoreCase("skyblock_nether")) {
				if (player.isFlying()) {
					player.setFlying(false);
					player.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6Disabled flight."));
				}
				else {
					player.setFlying(true);
					player.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6Enabled flight."));
				}
			}
			else {
				player.sendMessage(Permissions.NO_PERM);
			}
		}
		return true;
	}

}

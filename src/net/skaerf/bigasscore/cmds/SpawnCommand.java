package net.skaerf.bigasscore.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.bigasscore.Events;
import net.skaerf.bigasscore.Permissions;

public class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				sender.sendMessage("Usage: /spawn <player>");
			}
			else {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					sender.sendMessage(Permissions.NOT_FOUND_PLAYER);
				}
				else {
					Location loc =  new Location(Bukkit.getWorld("spawn"), 0, 69, 0);
					target.teleport(loc);
					target.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6You were sent to spawn by Console."));
					sender.sendMessage(target.getName()+" was sent to spawn!");
				}
			}
		}
		else {
			Player player = (Player) sender;
			if (args.length == 0) {
				Location loc = new Location(Bukkit.getWorld("spawn"), 0, 69, 0);
				player.teleport(loc);
				player.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6You were sent to spawn."));
			}
			else {
				if (player.hasPermission(Permissions.CMD_SPAWN_OTHERS)) {
					Player target = Bukkit.getPlayerExact(args[0]);
					Location loc = new Location(Bukkit.getWorld("spawn"), 0, 69, 0);
					target.teleport(loc);
					target.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6You were sent to spawn by "+player.getName()+"."));
					sender.sendMessage(target.getName()+" was sent to spawn!");
				}
				else {
					player.sendMessage(Permissions.NO_PERM);
				}
			}
		}
		return true;
	}

}

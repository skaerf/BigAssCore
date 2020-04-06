package net.skaerf.bigasscore.cmds;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.skaerf.bigasscore.Events;
import net.skaerf.bigasscore.Permissions;

public class GamemodeCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Permissions.NO_CONSOLE);
		}
		else {
			Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("gms")) {
				if (player.hasPermission(Permissions.CMD_GMS)) {
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6Your gamemode was set to Survival!"));
				}
				else {
					player.sendMessage(Permissions.NO_PERM);
				}
			}
			else if (cmd.getName().equalsIgnoreCase("gmc")) {
				if (player.hasPermission(Permissions.CMD_GMC)) {
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6Your gamemode was set to Creative!"));
				}
				else {
					player.sendMessage(Permissions.NO_PERM);
				}
			}
			else if (cmd.getName().equalsIgnoreCase("gma")) {
				if (player.hasPermission(Permissions.CMD_GMA)) {
					player.setGameMode(GameMode.ADVENTURE);
					player.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6Your gamemode was set to Adventure!"));
				}
				else {
					player.sendMessage(Permissions.NO_PERM);
				}
			}
			else if (cmd.getName().equalsIgnoreCase("gmsp")) {
				if (player.hasPermission(Permissions.CMD_GMSP)) {
					player.setGameMode(GameMode.SPECTATOR);
					player.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6Your gamemode was set to Spectator!"));
				}
				else {
					player.sendMessage(Permissions.NO_PERM);
				}
			}
			else if (cmd.getName().equalsIgnoreCase("gm")) {
				player.sendMessage(Events.translate("&6&lOP&c&lNetwork &8&l>> &6Please use /gm<mode> i.e. /gms, /gmc"));
			}
		}
		return true;
	}

}

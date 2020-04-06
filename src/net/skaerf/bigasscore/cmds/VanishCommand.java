package net.skaerf.bigasscore.cmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.clip.placeholderapi.PlaceholderAPI;
import net.skaerf.bigasscore.ConfigManager;
import net.skaerf.bigasscore.Events;
import net.skaerf.bigasscore.Permissions;

public class VanishCommand implements CommandExecutor {
	
	private ConfigManager CFGm = new ConfigManager();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Permissions.NO_CONSOLE);
		}
		else {
			Player player = (Player) sender;
			if (player.hasPermission(Permissions.CMD_VANISH)) {
				if (args.length == 0) {
					if (CFGm.getMainConfig().getStringList("vanished").contains(player.getName())) {
						for (Player online : Bukkit.getOnlinePlayers()) {
							online.showPlayer(player);
						}
						List<String> vanished = CFGm.getMainConfig().getStringList("vanished");
						vanished.remove(player.getName());
						CFGm.getMainConfig().set("vanished", vanished);
						ScoreboardManager manager = Bukkit.getScoreboardManager();
						Scoreboard board = manager.getNewScoreboard();
						Team team = board.registerNewTeam("Names");
						team.addPlayer(player);
						team.setPrefix(PlaceholderAPI.setPlaceholders(player.getPlayer(), Events.translate("%vault_prefix%")));
						Objective health = board.registerNewObjective(Events.translate("&c&l❤"), "health");
						health.setDisplaySlot(DisplaySlot.BELOW_NAME);
						for (Player online : Bukkit.getOnlinePlayers()) {
							online.setScoreboard(board);
						}
						player.sendMessage(ChatColor.GREEN+"You were unvanished!");
					}
					else {
						for (Player online : Bukkit.getOnlinePlayers()) {
							if (!online.hasPermission(Permissions.CMD_VANISH_SEE)) {
								online.hidePlayer(player);
								
							}
						}
						ScoreboardManager manager = Bukkit.getScoreboardManager();
						Scoreboard board = manager.getNewScoreboard();
						Team team = board.registerNewTeam("Vanished");
						team.addPlayer(player);
						team.setPrefix(Events.translate("&8&l[&7V&8&l] &7"));
						Objective health = board.registerNewObjective(Events.translate("&c&l❤"), "health");
						health.setDisplaySlot(DisplaySlot.BELOW_NAME);
						player.setScoreboard(board);
						player.sendMessage(ChatColor.GREEN+"You were vanished!");
						List<String> vanished = CFGm.getMainConfig().getStringList("vanished");
						vanished.add(player.getName());
						for (Player online : Bukkit.getOnlinePlayers()) {
							if (online.hasPermission(Permissions.CMD_VANISH_SEE)) {
								online.setScoreboard(board);
							}
						}
						CFGm.getMainConfig().set("vanished", vanished);
					}
				}
				else {
					if (player.hasPermission(Permissions.CMD_VANISH_OTHERS)) {
						Player target = Bukkit.getPlayerExact(args[0]);
						if (target == null) {
							player.sendMessage(Permissions.NOT_FOUND_PLAYER);
						}
						else {
							for (Player online : Bukkit.getOnlinePlayers()) {
								if (!online.hasPermission(Permissions.CMD_VANISH_SEE)) {
									online.hidePlayer(target);
								}
							}
							ScoreboardManager manager = Bukkit.getScoreboardManager();
							Scoreboard board = manager.getNewScoreboard();
							Team team = board.registerNewTeam("Vanished");
							team.addPlayer(target);
							team.setDisplayName(Events.translate("&8&l[&7&lV&8&l]&7 Vanished"));
							Objective objective = board.registerNewObjective("Vanish", "dummy");
							objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
							target.setScoreboard(board);
							target.sendMessage(ChatColor.GREEN+"You were vanished!");
							player.sendMessage(ChatColor.GREEN+"You vanished "+target.getName()+"!");
						}
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

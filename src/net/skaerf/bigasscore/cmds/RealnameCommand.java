package net.skaerf.bigasscore.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RealnameCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			if (args.length != 1) {
				sender.sendMessage(ChatColor.GREEN+"Usage: /realname <nickname>");
			}
			else {
				//for (File playerFile : new File(BigassCore.getPlugin(BigassCore.class).getDataFolder()+File.separator+"playerdata").listFiles();
			}
		}
		return true;
	}
}

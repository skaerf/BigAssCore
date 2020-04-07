package net.skaerf.bigasscore.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardMan implements Listener {
	
	@EventHandler
	public void onWorldSwitch(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		if (player.getLocation().getWorld().getName().equalsIgnoreCase("skyblock")) {
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard board = manager.getNewScoreboard();
			Objective obj = board.registerNewObjective("scoreboard", "dummy");
			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			
			
		}
	}

}

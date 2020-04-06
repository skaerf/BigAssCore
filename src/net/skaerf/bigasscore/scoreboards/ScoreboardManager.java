package net.skaerf.bigasscore.scoreboards;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ScoreboardManager implements Listener {
	
	@EventHandler
	public void onWorldSwitch(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		
	}

}

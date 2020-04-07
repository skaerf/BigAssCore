package net.skaerf.bigasscore;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class BigassAPI {
	
	public static String pHolders(Player player, String placeholderTranslates) {
		return PlaceholderAPI.setPlaceholders(player, Events.translate(placeholderTranslates));
	}
	public static FileConfiguration getPlayerFile(String playerUUID) {
		FileConfiguration playercfg;
		File playerFile = new File(BigassCore.getPlugin(BigassCore.class).getDataFolder()+File.separator+"playerdata"+File.separator+playerUUID+".yml");
		if (!playerFile.exists()) {
			System.out.println(playerUUID+".yml does not exist, please create it first");
		}
		playercfg = YamlConfiguration.loadConfiguration(playerFile);
		return playercfg;
	}
	public static void newPlayerFile(String playerUUID) {
		File playerfile = new File(BigassCore.getPlugin(BigassCore.class).getDataFolder()+File.separator+"playerdata"+File.separator+playerUUID+".yml");
		if (!playerfile.exists()) {
			try {
				playerfile.createNewFile();
			}
			catch (IOException e) {
				System.out.println(playerUUID+".yml could not be created");
				e.printStackTrace();
			}
		}
	}

}

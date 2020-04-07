package net.skaerf.bigasscore;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class BigassAPI {
	
	public static FileConfiguration playercfg;
	
	public static String pHolders(Player player, String placeholderTranslates) {
		return PlaceholderAPI.setPlaceholders(player, Events.translate(placeholderTranslates));
	}
	public static FileConfiguration getPlayerFile(String playerUUID) {
		File playerFile = new File(BigassCore.getPlugin(BigassCore.class).getDataFolder()+File.separator+"playerdata"+File.separator+playerUUID+".yml");
		if (!playerFile.exists()) {
			System.out.println(playerUUID+".yml does not exist, please create it first");
		}
		playercfg = YamlConfiguration.loadConfiguration(playerFile);
		return playercfg;
	}
	public static void savePlayerFile(String playerUUID) {
		File playerFile = new File(BigassCore.getPlugin(BigassCore.class).getDataFolder()+File.separator+"playerdata"+File.separator+playerUUID+".yml");
		if (!playerFile.exists()) {
			System.out.println(playerUUID+".yml does not exist, please create it first");
		}
		try {
			playercfg.save(playerUUID);
		}
		catch (IOException e) {
			System.out.println(playerUUID+".yml could not be saved");
		}
	}
	public static void reloadPlayerFile(String playerUUID) {
		File playerFile = new File(BigassCore.getPlugin(BigassCore.class).getDataFolder()+File.separator+"playerdata"+File.separator+playerUUID+".yml");
		if (!playerFile.exists()) {
			System.out.println(playerUUID+".yml does not exist, please create it first");
		}
		YamlConfiguration.loadConfiguration(playerFile);
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
	public static void newClanFile(String clanName) {
		File clanFile = new File(BigassCore.getPlugin(BigassCore.class).getDataFolder()+File.separator+"clans"+File.separator+clanName+".yml");
		if (!clanFile.exists()) {
			try {
				clanFile.createNewFile();
			}
			catch (IOException e) {
				System.out.println(clanName+".yml could not be created");
			}
		}
		else {
			System.out.println("there is already a clan file under the name "+clanName+", skipping");
		}
	}
	public static FileConfiguration getClanFile(String clanName) {
		File clanFile = new File(BigassCore.getPlugin(BigassCore.class).getDataFolder()+File.separator+"clans"+File.separator+clanName+".yml");
		if (!clanFile.exists()) {
			System.out.println(clanName+".yml doesn't exist, please create it first");
		}
		FileConfiguration clancfg = YamlConfiguration.loadConfiguration(clanFile);
		return clancfg;
	}
	public static FileConfiguration getBigassConfig() {
		return BigassCore.getPlugin(BigassCore.class).getConfig();
	}
	public static void saveBigassConfig() {
		BigassCore.getPlugin(BigassCore.class).saveConfig();
	}
	public static void reloadBigassConfig() {
		BigassCore.getPlugin(BigassCore.class).reloadConfig();
	}

}

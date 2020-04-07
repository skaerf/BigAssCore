package net.skaerf.bigasscore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
	
	private BigassCore plugin = BigassCore.getPlugin(BigassCore.class);
	
	// Files and File Configs Here //
	public FileConfiguration punishmentsCFG;
	public FileConfiguration playerCFG;
	public FileConfiguration sWordsCFG;
	public FileConfiguration clanCFG;
	public File cFolder;
	public File clanFile;
	public File sWords;
	public File pFolder;
	public File playerData;
	public File punishments;
	
	public void reloadAll() {
		saveMainConfig();
		reloadMainConfig();
		savePunishments();
		reloadPunishments();
		System.out.println("all main configs reloaded");
	}
	
	// MAIN CONFIG //
	public void saveMainConfig() {
		plugin.saveConfig();
	}
	public void reloadMainConfig() {
		plugin.reloadConfig();
	}
	public FileConfiguration getMainConfig() {
		return plugin.getConfig();
	}
	
	// Colors without translation sign
	public String trans = "§";
	
	// PLAYERS FOLDER and NEW FILES //
	public void setupPlayerDataFolder() {
		pFolder = new File(plugin.getDataFolder(), "playerdata");
		if (!pFolder.exists()) {
			try {
				pFolder.mkdir();
				System.out.println("playerdata folder was created");
			}
			catch (NullPointerException e) {
				System.out.println("playerdata folder could not be created");
			}
		}
	}
	public void newPlayerDataFile(String uuid) {
		playerData = new File(plugin.getDataFolder()+File.separator+"playerdata"+File.separator, uuid+".yml");
		if (!playerData.exists()) {
			try {
				playerData.createNewFile();
				System.out.println(uuid+" file was created in playerdata");
			}
			catch (IOException e) {
				System.out.println(uuid+" file could not be created");
			}
		}
		else {
			System.out.println("player already has a file, skipping");
		}
	}
	public FileConfiguration getPlayerFile(String uuid) {
		playerData = new File(plugin.getDataFolder()+File.separator+"playerdata"+File.separator+uuid+".yml");
		playerCFG = YamlConfiguration.loadConfiguration(playerData);
		return playerCFG;
	}
	public void savePlayerFile(String uuid) {
		try {
			playerCFG.save(playerData);
		}
		catch (IOException e) {
			System.out.println("could not save "+uuid+".yml: savePlayerFile() IOException");
		}
	}
	public void reloadPlayerFile(String uuid) {
		playerCFG = YamlConfiguration.loadConfiguration(playerData);
		System.out.println(uuid+".yml reloaded");
	}
	// CLANS FOLDER //
	public void setupClansFolder() {
		cFolder = new File(plugin.getDataFolder(), "clans");
		if (!cFolder.exists()) {
			try {
				cFolder.mkdir();
				System.out.println("clan folder was created");
			}
			catch (NullPointerException e) {
				System.out.println("clan folder could not be created");
			}
		}
	}
	public void newClanFile(String clanName) {
		clanFile = new File(plugin.getDataFolder()+File.separator+"clans"+File.separator+clanName+".yml");
		if (!clanFile.exists()) {
			try {
				clanFile.createNewFile();
				System.out.println(clanName+".yml was created in clans folder");
			}
			catch (IOException e) {
				System.out.println(clanName+".yml could not be created");
			}
		}
		else {
			System.out.println("there is already a clan file under the name "+clanName+", skipping");
		}
	}
	public FileConfiguration getClanFile(String clanName) {
		clanFile = new File(plugin.getDataFolder()+File.separator+"clans"+File.separator+clanName+".yml");
		clanCFG = YamlConfiguration.loadConfiguration(clanFile);
		return clanCFG;
	}
	public void saveClanFile(String clanName) {
		try {
			clanCFG.save(clanFile);
		}
		catch (IOException e) {
			System.out.println(clanName+".yml could not be saved");
		}
	}
	public void reloadClanFile(String clanName) {
		clanCFG = YamlConfiguration.loadConfiguration(clanFile);
		System.out.println(clanName+".yml reloaded");
	}
	// SWEARWORDS.YML //
	public void setupSWords() {
		sWords = new File(plugin.getDataFolder(), "swearwords.yml");
		if (!sWords.exists()) {
			try {
				sWords.createNewFile();
				System.out.println("swearwords.yml was created!");
			}
			catch (IOException e) {
				System.out.println("swearwords.yml could not be created");
			}
		}
		sWordsCFG = YamlConfiguration.loadConfiguration(sWords);
		List<String> words = new ArrayList<String>();
		words.add("swearword");
		sWordsCFG.set("words", words);
		System.out.println("swearwords.yml was loaded!");
	}
	public FileConfiguration getSWords() {
		return sWordsCFG;
	}
	public void saveSWords() {
		try {
			sWordsCFG.save(sWords);
		}
		catch (IOException e) {
			System.out.println("could not save swearwords.yml");
		}
	}
	public void reloadSWords() {
		sWordsCFG = YamlConfiguration.loadConfiguration(sWords);
		System.out.println("swearwords.yml was reloaded");
	}
	// PUNISHMENTS.YML //
	public void setupPunishments() {
		punishments = new File(plugin.getDataFolder(), "punishments.yml");
		if (!punishments.exists()) {
			try {
				punishments.createNewFile();
				System.out.println("punishments.yml was created");
			}
			catch (IOException e) {
				System.out.println("punishments.yml could not be created");
			}
		}
		punishmentsCFG = YamlConfiguration.loadConfiguration(punishments);
		System.out.println("punishments.yml was loaded");
	}
	public FileConfiguration getPunishments() {
		return punishmentsCFG;
	}
	public void savePunishments() {
		try {
			punishmentsCFG.save(punishments);
		}
		catch (IOException e) {
			System.out.println("could not save punishments.yml");
		}
	}
	public void reloadPunishments() {
		punishmentsCFG = YamlConfiguration.loadConfiguration(punishments);
		System.out.println("punishments.yml was reloaded");
	}
	// ECONOMY.YML //
	public void setupEconomy() {
		
	}
	

}

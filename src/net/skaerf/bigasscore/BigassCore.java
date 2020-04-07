package net.skaerf.bigasscore;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.skaerf.bigasscore.cmds.BigassCoreCommand;
import net.skaerf.bigasscore.cmds.ClanCommand;
import net.skaerf.bigasscore.cmds.GamemodeCommand;
import net.skaerf.bigasscore.cmds.NickCommand;
import net.skaerf.bigasscore.cmds.RealnameCommand;
import net.skaerf.bigasscore.cmds.VanishCommand;
import net.skaerf.bigasscore.cmds.WhoisCommand;

public class BigassCore extends JavaPlugin {
	
	private ConfigManager CFGm;
	public HashMap<UUID, Double> playerBank = new HashMap<>();
	
	@Override
	public void onEnable() {
		loadConfigManager();
		loadCMDS();
		loadPapi();
	}
	public void loadConfigManager() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		this.saveDefaultConfig();
		this.reloadConfig();
		CFGm = new ConfigManager();
		// player yml files
		CFGm.setupPlayerDataFolder();
		// swearwords.yml
		CFGm.setupSWords();
		CFGm.saveSWords();
		CFGm.reloadSWords();
		// punishments.yml
		CFGm.setupPunishments();
		CFGm.savePunishments();
		CFGm.reloadPunishments();
		// clan yml files
		CFGm.setupClansFolder();
	}
	public void loadCMDS() {
		getServer().getPluginManager().registerEvents(new Events(), this);
		this.getCommand("nick").setExecutor(new NickCommand());
		this.getCommand("vanish").setExecutor(new VanishCommand());
		this.getCommand("clan").setExecutor(new ClanCommand());
		this.getCommand("bigasscore").setExecutor(new BigassCoreCommand());
		this.getCommand("gms").setExecutor(new GamemodeCommand());
		this.getCommand("gma").setExecutor(new GamemodeCommand());
		this.getCommand("gmc").setExecutor(new GamemodeCommand());
		this.getCommand("gmsp").setExecutor(new GamemodeCommand());
		this.getCommand("gm").setExecutor(new GamemodeCommand());
		this.getCommand("whois").setExecutor(new WhoisCommand());
		this.getCommand("realname").setExecutor(new RealnameCommand());
		
		System.out.println("loaded CMDs");
	}
	public void loadPapi() {
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
			new Placeholders().register();
			System.out.println("placeholderapi is installed, starting");
		}
	}

}

package net.skaerf.bigasscore;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.clip.placeholderapi.PlaceholderAPI;

public class Events implements Listener {
	
	public static String translate(String translate) {
		return ChatColor.translateAlternateColorCodes('&', translate);
	}
	
	private ConfigManager CFGm = new ConfigManager();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		String msg = event.getMessage();
		event.setCancelled(true);
		String formatglobal = CFGm.getMainConfig().getString("chat.format.global");
		String formattedglobal = PlaceholderAPI.setPlaceholders(event.getPlayer(), formatglobal);
		if (CFGm.getMainConfig().getString("chat.global").equalsIgnoreCase("false")) {
			String playerworld = event.getPlayer().getLocation().getWorld().getName();
			if (playerworld.equalsIgnoreCase("skyblock")) {
				String formatskyblock = null;
				if (event.getPlayer().hasPermission(Permissions.CHAT_RANKED)) {
					formatskyblock = CFGm.getMainConfig().getString("chat.format.ranked.skyblock");
				}
				else {
					formatskyblock = CFGm.getMainConfig().getString("chat.format.default.skyblock");
				}
				String formattedskyblock = PlaceholderAPI.setPlaceholders(event.getPlayer(), formatskyblock);
				for (Player online : Bukkit.getOnlinePlayers()) {
					if (online.getLocation().getWorld().getName().equalsIgnoreCase(playerworld)) {
						if (event.getPlayer().hasPermission(Permissions.CHAT_COLOR)) {
							online.sendMessage(Events.translate(formattedskyblock+msg));
						}
						else {
							online.sendMessage(formattedskyblock+msg);
						}
					}
				}
			}
			else {
				String formatspawn = null;
				if (event.getPlayer().hasPermission(Permissions.CHAT_RANKED)) {
					formatspawn = CFGm.getMainConfig().getString("chat.format.ranked.spawn");
				}
				else {
					formatspawn = CFGm.getMainConfig().getString("chat.format.default.spawn");
				}
				String formattedspawn = PlaceholderAPI.setPlaceholders(event.getPlayer(), formatspawn);
				for (Player online : Bukkit.getOnlinePlayers()) {
					if (online.getLocation().getWorld().getName().equalsIgnoreCase(playerworld)) {
						if (event.getPlayer().hasPermission(Permissions.CHAT_COLOR)) {
							online.sendMessage(Events.translate(formattedspawn+msg));
						}
						else {
							online.sendMessage(formattedspawn+msg);
						}
					}
				}
			}
		}
		else {
			for (Player online : Bukkit.getOnlinePlayers()) {
				online.sendMessage(formattedglobal+msg);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		// BAN SYSTEM //
		// add ip scanner - check for alts
		//String port = event.getPlayer().getAddress().toString();
		//String[] ip = port.split(":");
		//if (CFGm.getPunishments().getStringList("ipbans").contains(ip[0])) {
	//		event.getPlayer().kickPlayer(Events.translate("&6&lOP&c&lNetwork&c &8&l>>\n&c&lYou are permanently IP-banned from OPNetwork!\n\n&7You cannot appeal for an IPban."));
		//		return;
		//}
		//else if (CFGm.getPunishments().getStringList("bans").contains(event.getPlayer().getUniqueId().toString())) {
			//event.getPlayer().kickPlayer(Events.translate("&6&lOP&c&lNetwork&c &8&l>>\n&c&lYou are permanently banned from OPNetwork!\n\n&7You can appeal:\n&c&lDiscord: &adiscord.opnetwork.xyz\n&c&lForums: &aComing Soon"));
			//return;
		//}
		//else {
			//String x = "";
			//for (String s : CFGm.getPunishments().getStringList("tempbans")) {
	//			String[] array = s.split(":");
		//			if (array[0].contentEquals(event.getPlayer().getUniqueId().toString())) {
			//			long time = Long.parseLong(array[1]) - System.currentTimeMillis();
				//		double hours = TimeUnit.MILLISECONDS.toHours(time);
					//	if (System.currentTimeMillis() < Long.parseLong(array[1])) {
						//	if (hours > 1) {
		//						event.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes(
			//							'&', "&c&lYou are temporarily banned from &6&lOP&c&lNetwork&c!"
				//								+ "\nYou will be unbanned in: " 
					//							+ hours + " hours."));
						//	}
							//else {
//								event.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes(
	//									'&', "&c&lYou are temporarily banned from &6&lOP&c&lNetwork&c!"
		//										+ "\nYou will be unbanned within the next hour."));
		//					}
			//				x = s;
				//		break;							}
					//}
//					else {
	//					List<String> playerbanned = CFGm.getPunishments().getStringList("tempbans");
		//				playerbanned.remove(x);
			//			CFGm.getPunishments().set("tempbans", playerbanned);
				//		CFGm.savePunishments();
					//	CFGm.reloadPunishments();
//					}
	//			}
		//	}
		if (CFGm.getMainConfig().getString("spawn-on-join").equalsIgnoreCase("true")) {
			World w = Bukkit.getServer().getWorld(CFGm.getMainConfig().getString("spawn.world"));
            float yaw = (float) 0;
            float pitch = (float) 0;
            double x = 0;
            double y = 69;
            double z = 0;
            Location loc = new Location (w, x, y, z);
            loc.setYaw(yaw);
            event.getPlayer().getLocation().setPitch(pitch);
            event.getPlayer().teleport(loc);
		}
		event.setJoinMessage(PlaceholderAPI.setPlaceholders(event.getPlayer(), CFGm.getMainConfig().getString("join-message")));
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Team team = board.registerNewTeam("Names");
		team.addPlayer(event.getPlayer());
		Objective health = board.registerNewObjective(Events.translate("&c&l❤"), "health");
		health.setDisplaySlot(DisplaySlot.BELOW_NAME);
		team.setPrefix(PlaceholderAPI.setPlaceholders(event.getPlayer(), Events.translate("%vault_prefix%")));
		event.getPlayer().setScoreboard(board);
		for (Player online : Bukkit.getOnlinePlayers()) {
			online.setScoreboard(board);
		}
		if (event.getPlayer().hasPermission(Permissions.EVENT_STAFF_JOIN)) {
			event.setJoinMessage("");
			for (Player online : Bukkit.getOnlinePlayers()) {
				if (online.hasPermission(Permissions.EVENT_STAFF_JOIN)) {
					online.sendMessage(Events.translate("&b[&3Staff&b] &l"+event.getPlayer().getName()+" &bhas joined."));
				}
			}
		}
		// Sound Effect
		//Location location = event.getPlayer().getLocation();
		//event.getPlayer().playSound(location, Sound.ENTITY_ENDER_DRAGON_FLAP, 2F, 1F);
		// Player Data File
		String uuid = event.getPlayer().getUniqueId().toString();
		CFGm.newPlayerDataFile(uuid);
		FileConfiguration playerFile = CFGm.getPlayerFile(uuid);
		playerFile.set("name", event.getPlayer().getName());
		playerFile.set("uuid", uuid);
		List<String> ipadresses = playerFile.getStringList("ips");
		String iplong = event.getPlayer().getAddress().toString();
		String[] playerIP = iplong.split(":");
		if (!ipadresses.contains(playerIP[0])) {
			ipadresses.add(playerIP[0]);
			playerFile.set("ips", ipadresses);
		}
		if (playerFile.getStringList("homes") == null) {
			playerFile.set("homes", "unset");
		}
		if (playerFile.getString("nickname") == null) {
			playerFile.set("nickname", event.getPlayer().getName());
		}
		if (playerFile.getString("clan") == null) {
			playerFile.set("clan", "unset");
		}
		CFGm.savePlayerFile(uuid);
		CFGm.reloadPlayerFile(uuid);
		// Join Title/Messages
		if (CFGm.getMainConfig().getString("join.enabled").equalsIgnoreCase("true")) {
			if (CFGm.getMainConfig().getString("join.title.enabled").equalsIgnoreCase("true")) {
				event.getPlayer().sendTitle(Events.translate(CFGm.getMainConfig().getString("join.title.title")), Events.translate(CFGm.getMainConfig().getString("join.title.subtitle")));
			}
			if (CFGm.getMainConfig().getString("join.message.enabled").equalsIgnoreCase("true")) {
				event.getPlayer().sendMessage(Events.translate(CFGm.getMainConfig().getString("join.message.message").replace("%player%", event.getPlayer().getDisplayName())));
			}
		}
	}
	@EventHandler
	public void onPing(ServerListPingEvent event) throws IllegalArgumentException, UnsupportedOperationException, Exception {
		CFGm.saveMainConfig();
		CFGm.reloadMainConfig();
		String motd = CFGm.getMainConfig().getString("motd");
		event.setMotd(Events.translate(motd));
		int pLimit = CFGm.getMainConfig().getInt("playerlimit");
		event.setMaxPlayers(pLimit);
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		if (event.getPlayer().hasPermission(Permissions.EVENT_STAFF_JOIN)) {
			event.setQuitMessage("");
			for (Player online : Bukkit.getOnlinePlayers()) {
				if (online.hasPermission(Permissions.EVENT_STAFF_JOIN)) {
					online.sendMessage(Events.translate("&b[&3Staff&b] &l"+event.getPlayer().getName()+" &bleft."));
				}
			}
		}
	}
	@SuppressWarnings("unused")
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		Inventory inv = event.getInventory();
		// /grant GUI user selection
		if (inv.getName().equals(ChatColor.GREEN+"Select a Player")) {
			ItemStack clicked = event.getCurrentItem();
			String name = clicked.getItemMeta().getDisplayName();
			event.setCancelled(true);
			player.closeInventory();
			
		}
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
	}

}

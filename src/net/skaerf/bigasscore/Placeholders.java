package net.skaerf.bigasscore;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Placeholders extends PlaceholderExpansion {
	
	private ConfigManager CFGm = new ConfigManager();
	private BigassCore plugin;
	
	public void SomeExpansion(BigassCore plugin) {
        this.plugin = plugin;
    }
	@Override
    public boolean persist(){
        return true;
    }

	@Override
	public String getAuthor() {
		return plugin.getDescription().getAuthors().toString();
	}

	@Override
	public String getIdentifier() {
		return "BigassCore";
	}

	@Override
	public String getVersion() {
		return plugin.getDescription().getVersion();
	}

	@Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }

        // %bigasscore_nickname%
        if(identifier.equals("nickname")){
            return Events.translate(CFGm.getPlayerFile(player.getUniqueId().toString()).getString("nickname"));
        }
        // random stuff
        if (identifier.equals("skyblock_balance")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("skyblockbalance");
        }
        if (identifier.equals("factions_balance")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("factionsbalance");
        }
        if (identifier.equals("prison_balance")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("prisonbalance");
        }
        if (identifier.equals("name")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("name");
        }
        if (identifier.equals("uuid")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("uuid");
        }
        if (identifier.equals("first_join")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("firstjoin");
        }
        // Stats //
        // Skyblock
        if (identifier.equals("stats_skyblock_kills")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("skyblock.kills");
        }
        if (identifier.equals("stats_skyblock_deaths")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("skyblock.deaths");
        }
        if (identifier.equals("stats_skyblock_kdratio")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("skyblock.kdratio");
        }
        if (identifier.equals("stats_skyblock_killstreak")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("skyblock.killstreak");
        }
        // Factions
        if (identifier.equals("stats_factions_kills")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("factions.kills");
        }
        if (identifier.equals("stats_factions_deaths")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("factions.deaths");
        }
        if (identifier.equals("stats_factions_kdratio")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("factions.kdratio");
        }
        if (identifier.equals("stats_factions_killstreak")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("factions.killstreak");
        }
        // Prison
        if (identifier.equals("stats_prison_kills")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("prison.kills");
        }
        if (identifier.equals("stats_prison_deaths")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("prison.deaths");
        }
        if (identifier.equals("stats_prison_kdratio")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("prison.kdratio");
        }
        if (identifier.equals("stats_prison_killstreak")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("prison.killstreak");
        }
        // Home //
        if (identifier.equals("home_amount")) {
        	return CFGm.getPlayerFile(player.getUniqueId().toString()).getString("homes.count");
        }
        // Clans //
        // do clans
        return null;
    }
}

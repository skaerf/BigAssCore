package net.skaerf.bigasscore.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GrantGUIs {
	
	@SuppressWarnings("unused")
	public static void selUserGrantGUI(Player player) {
		int playercount = 0;
		for (Player online : Bukkit.getOnlinePlayers()) {
			playercount = playercount + 1;
		}
		Inventory usersel = Bukkit.createInventory(null, playercount, ChatColor.GREEN+"Select a Player");
		for (Player online : Bukkit.getOnlinePlayers()) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1);
			SkullMeta meta = (SkullMeta) skull.getItemMeta();
			skull.setDurability((short)3);
			meta.setOwner(online.getName());
			meta.setDisplayName(ChatColor.AQUA+online.getName());
			List<String> lores = new ArrayList<String>();
			lores.add(ChatColor.GREEN+"Click me to edit!");
			meta.setLore(lores);
			skull.setItemMeta(meta);
			usersel.addItem(skull);
		}
		player.openInventory(usersel);
	}

}

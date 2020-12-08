package net.mineset.hubby.managers;

import net.mineset.hubby.Hubby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class InventoryManager {

    private Inventory gamesInventory;

    public InventoryManager () {
        gamesMenu();
    }

    public void gamesMenu() {
        gamesInventory = Bukkit.createInventory(null, 27, "Games Menu");

        ItemStack duels = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta duelsMeta = duels.getItemMeta();
        ArrayList<String> duelsLore = new ArrayList<>();

        new BukkitRunnable() {
            boolean toggled = true;

            @Override
            public void run() {
                duelsLore.clear();
                duelsLore.add("§7PvP, Competitive");
                duelsLore.add("");
                duelsLore.add("§f Go head to head against your enemies");
                duelsLore.add("§f and train and rank your way up the ladder!");
                duelsLore.add("");
                duelsLore.add("§e" + (toggled ? "§e➟" : "§7➟") + "§e Right-Click to join the server");
                duelsLore.add("§b" + (toggled ? "§b➟" : "§7➟") + "§b Left-Click to warp to the npc");
                duelsMeta.setDisplayName("§d§lDuels");
                duelsMeta.setLore(duelsLore);
                duels.setItemMeta(duelsMeta);

                gamesInventory.setItem(11, duels);
                toggled = !toggled;
            }
        }.runTaskTimer(Hubby.plugin, 20L, 20L);

        ItemStack freeForAll = new ItemStack(Material.FIREWORK, 1);
        ItemMeta freeForAllMeta = freeForAll.getItemMeta();
        ArrayList<String> freeForAllLore = new ArrayList<>();

        new BukkitRunnable() {
            boolean toggled = true;

            @Override
            public void run() {
                freeForAllLore.clear();
                freeForAllLore.add("§7PvP, Competitive");
                freeForAllLore.add("");
                freeForAllLore.add("§f Fight your way through an army");
                freeForAllLore.add("§f of other players looking to excel");
                freeForAllLore.add("§f the leaderboards to the gods of mineset!");
                freeForAllLore.add("");
                freeForAllLore.add("§e" + (toggled ? "§e➟" : "§7➟") + "§e Right-Click to join the server");
                freeForAllLore.add("§b" + (toggled ? "§b➟" : "§7➟") + "§b Left-Click to warp to the npc");
                freeForAllMeta.setDisplayName("§b§lFree For all");
                freeForAllMeta.setLore(freeForAllLore);
                freeForAll.setItemMeta(freeForAllMeta);

                gamesInventory.setItem(15, freeForAll);
                toggled = !toggled;
            }
        }.runTaskTimer(Hubby.plugin, 20L, 20L);
    }

    public void openGamesMenu(Player player) {
        player.openInventory(gamesInventory);
    }
}

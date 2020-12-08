package net.mineset.hubby.managers;

import net.mineset.hubby.Hubby;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickManager implements Listener {

    public InventoryManager inventoryManager = Hubby.plugin.getInventoryManager();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!e.getInventory().getName().equalsIgnoreCase("")) {
            Player p = (Player)e.getWhoClicked();
            e.setCancelled(true);
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta()) {
                switch(e.getCurrentItem().getType()) {
                    case LEATHER_CHESTPLATE:
                    default:
                        switch(e.getCurrentItem().getType()) {
                            case FISHING_ROD:
                                Hubby.plugin.bungeeUtil.connect(p, "Duels");
                                p.sendMessage("§aYou enetered §b§lDUELS§a!");
                            default:
                        }
                }

                switch(e.getCurrentItem().getType()) {
                    case LEATHER_CHESTPLATE:
                    default:
                        switch(e.getCurrentItem().getType()) {
                            case FIREWORK:
                                Hubby.plugin.bungeeUtil.connect(p, "FFA");
                                p.sendMessage("§aYou enetered §b§lFFA§a!");
                            default:
                        }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        ItemStack item = event.getItem();
        Player player =  event.getPlayer();
        if (action != Action.PHYSICAL && item != null && item.getType() != Material.AIR) {

            if (item.getType() == Material.COMPASS) {
                inventoryManager.openGamesMenu(player);
            }

            if (item.getType() == Material.BOOK) {
            }

            if (item.getType() == Material.REDSTONE_COMPARATOR) {
            }

            if (item.getType() == Material.TRAPPED_CHEST) {
            }

        }
    }
}

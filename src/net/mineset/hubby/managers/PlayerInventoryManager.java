package net.mineset.hubby.managers;

import net.mineset.hubby.utils.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInventoryManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ItemStack compass = Items.createItem(Material.COMPASS, "§aGame Selector §7▪ Right-Click", 1);
        compass = Items.hideFlags(compass);
        player.getInventory().setItem(0, compass);

        ItemStack item = Items.createItem(Material.EYE_OF_ENDER, "§dFlying Pearl §7▪ Right-Click", 1);
        item = Items.hideFlags(item);
        player.getInventory().setItem(4, item);

        ItemStack chest = Items.createItem(Material.TRAPPED_CHEST, "§aCosmetics §7▪ Right-Click", 1);
        chest = Items.hideFlags(chest);
        player.getInventory().setItem(8, chest);
    }
}

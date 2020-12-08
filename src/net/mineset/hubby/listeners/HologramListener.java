package net.mineset.hubby.listeners;

import net.mineset.hubby.utils.Holo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HologramListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Location drop_holo = new Location(Bukkit.getWorld("Map"),  -7.5, 92, 0.5);
        Holo drop = new Holo(drop_holo, "§9§lMINESET", "§7Welcome, Use the NPCs to play a game!");
        drop.display(player);

        Location duels = new Location(Bukkit.getWorld("Map"),  -49.5, 93.0, 0.5);
        Holo drop1 = new Holo(duels, "§d§lDuels", "§7Click to play!");
        drop1.display(player);

        Location skywars = new Location(Bukkit.getWorld("Map"),  -48.5, 93, 3.5);
        Holo drop2 = new Holo(skywars, "§b§lFree For All", "§7Click to play!");
        drop2.display(player);

        Location bedwars = new Location(Bukkit.getWorld("Map"),  -48.5, 93, -2.5);
        Holo drop3 = new Holo(bedwars, "§c§lEVENTS", "§7Click to play!");
        drop3.display(player);
    }
}

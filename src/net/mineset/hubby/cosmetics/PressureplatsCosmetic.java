package net.mineset.hubby.cosmetics;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class PressureplatsCosmetic implements Listener {

    private ArrayList<Player> jumpers = new ArrayList<Player>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SLIME_BLOCK) {
            event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().multiply(2));
            event.getPlayer().setVelocity(new Vector(event.getPlayer().getVelocity().getX(), 1.75D, event.getPlayer().getVelocity().getZ()));

            jumpers.add(event.getPlayer());

            Player player = event.getPlayer();
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 0);
        }
    }
}
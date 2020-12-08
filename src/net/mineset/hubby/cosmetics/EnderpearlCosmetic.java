package net.mineset.hubby.cosmetics;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import net.mineset.hubby.Hubby;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EnderpearlCosmetic implements Listener {

    private static Set<UUID> cooldown = new HashSet<>();

    public static void setup() {

        new BukkitRunnable() {

            public void run() {

                for (Player player : Bukkit.getOnlinePlayers()) {

                    if (player.getVehicle() == null || player.getVehicle().getType() != EntityType.ENDER_PEARL) continue;

                    player.spigot().playEffect(player.getLocation(), Effect.INSTANT_SPELL, 14, 0, 0f, 0f, 0f, 0.20f, 50, 1);

                }

            }

        }.runTaskTimer(Hubby.plugin, 1, 1);

    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();

        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;
        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) return;

        String name = item.getItemMeta().getDisplayName();

        if (!name.startsWith("§dFlying Pearl")) return;

        event.setCancelled(true);

        if (cooldown.contains(player.getUniqueId()) || player.getVehicle() != null) return;

        cooldown.add(player.getUniqueId());

        new BukkitRunnable() {

            int left = 5;

            public void run() {

                if (left < 1) {

                    cooldown.remove(player.getUniqueId());
                    cancel();
                    return;

                }

                ActionBarAPI.sendActionBar(player, "§dYou can use your Flying Pearl in " + left + "s");
                left--;

            }

        }.runTaskTimer(Hubby.plugin, 0, 20);

        EnderPearl pearl = player.launchProjectile(EnderPearl.class);
        pearl.setPassenger(player);
        player.spigot().setCollidesWithEntities(false);
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);

    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {

        Player player = event.getPlayer();

        if (event.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL) return;

        event.setCancelled(true);
        player.teleport(player.getLocation().add(0, 1, 0));
        player.spigot().setCollidesWithEntities(true);

    }

    @EventHandler
    public void onDismount(EntityDismountEvent event) {

        if (!(event.getDismounted() instanceof EnderPearl)) return;
        if (!(event.getEntity() instanceof Player)) return;

        EnderPearl pearl = (EnderPearl) event.getDismounted();
        Player player = (Player) event.getEntity();

        pearl.remove();
        player.spigot().setCollidesWithEntities(true);

    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {

        if (!(event.getEntity() instanceof EnderPearl)) return;

        EnderPearl pearl = (EnderPearl) event.getEntity();

        if (!(pearl.getShooter() instanceof Player)) return;

        Player player = (Player) pearl.getShooter();

        pearl.remove();
        player.teleport(player.getLocation().add(0, 0.5, 0));
        player.spigot().setCollidesWithEntities(true);

    }

}
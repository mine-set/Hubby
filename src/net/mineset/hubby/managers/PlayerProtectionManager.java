package net.mineset.hubby.managers;

import net.mineset.hubby.Hubby;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.List;

public class PlayerProtectionManager implements Listener {

    public static void setup() {

        Bukkit.getScheduler().runTaskTimer(Hubby.plugin, () -> Bukkit.getOnlinePlayers().stream().forEach(p -> p.setFireTicks(0)), 20, 20);

    }

    @EventHandler
    public void onPlayerDrop(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location loc = p.getLocation();
        World world = Bukkit.getServer().getWorld("Map");
        List<Entity> entList = world.getEntities();

        for (Entity current : entList) {
            if (current instanceof Item) {
                current.remove();
            }
        }

        if (loc.getBlockY() < 60) {
            Location spawn = new Location(Bukkit.getWorld("Map"),  0.5, 91, 0.5, 90,0);
            p.teleport(spawn);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onBlockDamage(BlockDamageEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onManipulateArmorStand(PlayerArmorStandManipulateEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onBed(PlayerBedEnterEvent event) {

        event.setCancelled(true);

    }

    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);
        event.getBlockClicked().getState().update(true, true);

    }

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);
        event.getBlockClicked().getState().update(true, true);

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onExp(PlayerExpChangeEvent event) {

        event.setAmount(0);

    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {

        event.setCancelled(true);

    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onShear(PlayerShearEntityEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onCombust(EntityCombustEvent event) {

        event.setCancelled(true);

    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        event.setCancelled(true);

    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) {

            event.setCancelled(true);
            return;

        }

        Player player = (Player) event.getDamager();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onHungerChange(FoodLevelChangeEvent event) {

        event.setCancelled(true);
        event.setFoodLevel(20);

    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        switch (entity.getType()) {

            case ARMOR_STAND:
                return;
            default:
                event.setCancelled(true);
                break;

        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        switch (event.getAction()) {

            case PHYSICAL:
                event.setCancelled(true);
                return;
            case RIGHT_CLICK_BLOCK:

                switch (event.getClickedBlock().getType()) {

                    case ANVIL:
                    case BEACON:
                    case BREWING_STAND:
                    case CHEST:
                    case WORKBENCH:
                    case DISPENSER:
                    case DROPPER:
                    case ENCHANTMENT_TABLE:
                    case ENDER_CHEST:
                    case FURNACE:
                    case BURNING_FURNACE:
                    case HOPPER:
                    case ITEM_FRAME:
                    case LEVER:
                    case BED_BLOCK:
                    case TRAPPED_CHEST:
                        event.setCancelled(true);
                        return;
                    default:
                        break;

                }

                break;
            default:
                break;

        }

    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {

        event.setCancelled(true);

    }

    @EventHandler
    public void onGrow(BlockGrowEvent event) {

        if (!event.getNewState().getType().equals(Material.SUGAR_CANE_BLOCK)) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onMoveItem(InventoryMoveItemEvent event) {

        event.setCancelled(true);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        if (player.hasPermission("global.build") && player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);

    }

}

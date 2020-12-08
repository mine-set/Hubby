package net.mineset.hubby;

import net.mineset.hubby.cosmetics.EnderpearlCosmetic;
import net.mineset.hubby.cosmetics.PressureplatsCosmetic;
import net.mineset.hubby.listeners.*;
import net.mineset.hubby.managers.InventoryClickManager;
import net.mineset.hubby.managers.InventoryManager;
import net.mineset.hubby.managers.PlayerInventoryManager;
import net.mineset.hubby.managers.PlayerProtectionManager;
import net.mineset.hubby.utils.BungeeUtil;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class Hubby extends JavaPlugin {

    public static Hubby plugin;

    public InventoryManager inventoryManager;
    public BungeeUtil bungeeUtil;

    @Override
    public void onEnable() {

        plugin = this;

        inventoryManager = new InventoryManager();
        bungeeUtil = new BungeeUtil(this);

        Bukkit.getServer().getPluginManager().registerEvents(new EnderpearlCosmetic(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PressureplatsCosmetic(), this);

        Bukkit.getServer().getPluginManager().registerEvents(new HologramListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new NameMCListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ScoreboardListener(this), this);

        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClickManager(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInventoryManager(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerProtectionManager(), this);

        EnderpearlCosmetic.setup();
        PlayerProtectionManager.setup();

        Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
        Bukkit.setIdleTimeout(0);
        Bukkit.setSpawnRadius(0);

        for (World world : Bukkit.getWorlds()) {

            world.getWorldBorder().reset();
            world.setDifficulty(Difficulty.NORMAL);
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setGameRuleValue("doEntityDrops", "false");
            world.setGameRuleValue("doFireTick", "false");
            world.setGameRuleValue("doMobLoot", "false");
            world.setGameRuleValue("doMobSpawning", "false");
            world.setGameRuleValue("mobGriefing", "false");
            world.setPVP(false);
            world.setSpawnFlags(false, false);
            world.setStorm(false);
            world.setThundering(false);
            world.setTime(6000);

        }
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
    public BungeeUtil getBungeeUtil() {
        return bungeeUtil;
    }
}

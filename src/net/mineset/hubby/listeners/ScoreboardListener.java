package net.mineset.hubby.listeners;


import net.mineset.hubby.Hubby;
import net.mineset.hubby.permissions.InternalServerRank;
import net.mineset.hubby.permissions.RankFinder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Iterator;

public class ScoreboardListener implements Listener {
    private Hubby main;
    private int taskID = 7231633;
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private HashMap<Player, Integer> task = new HashMap();
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private HashMap<Player, Scoreboard> scoreboard = new HashMap();

    @SuppressWarnings("rawtypes")
    public ScoreboardListener(Hubby main) {
        this.main = main;
        main.getServer().getPluginManager().registerEvents(this, main);
        Iterator var3 = Bukkit.getOnlinePlayers().iterator();

        while(var3.hasNext()) {
            Player ps = (Player)var3.next();
            ps.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
            if (!this.task.containsKey(ps)) {
                this.scoreboard.put(ps, Bukkit.getServer().getScoreboardManager().getNewScoreboard());
                this.boardStart(ps);
            }
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        if (!this.task.containsKey(event.getPlayer())) {
            this.scoreboard.put(event.getPlayer(), Bukkit.getServer().getScoreboardManager().getNewScoreboard());
            this.boardStart(event.getPlayer());
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent event) {
        if (this.task.containsKey(event.getPlayer())) {
            this.scoreboard.remove(event.getPlayer());
            Bukkit.getScheduler().cancelTask((Integer)this.task.get(event.getPlayer()));
            this.task.remove(event.getPlayer());
            event.getPlayer().getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        }

    }

    @SuppressWarnings("deprecation")
    public void boardStats(Player player) {
        Scoreboard board = (Scoreboard)this.scoreboard.get(player);
        InternalServerRank playerRank = RankFinder.GetRank(player);
        Objective objectiveSidebar = board.getObjective("sidebar");

        if (objectiveSidebar == null) {
            objectiveSidebar = board.registerNewObjective("sidebar", "abc");
            objectiveSidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
            objectiveSidebar.setDisplayName("§9§lMINESET");
            objectiveSidebar.getScore("§r").setScore(8);
            objectiveSidebar.getScore("§fOnline: §a" + Bukkit.getOnlinePlayers().size()).setScore(7);
            objectiveSidebar.getScore("§r§r").setScore(6);
            objectiveSidebar.getScore("§fName: §a" + player.getName()).setScore(5);
            objectiveSidebar.getScore("§fLevel: §a0").setScore(4);
            objectiveSidebar.getScore("§fRank: " + playerRank.Prefix).setScore(3);
            objectiveSidebar.getScore("§r§r§r").setScore(2);
            objectiveSidebar.getScore("§9play.mineset.net").setScore(1);
        }


        @SuppressWarnings("rawtypes")
        Iterator var7 = Bukkit.getOnlinePlayers().iterator();

        Player all;
        String prefix;
        String height;
        Team team;
        do {
            if (!var7.hasNext()) {
                return;
            }

            all = (Player)var7.next();
            prefix = null;
            height = null;
            if (all.hasPermission("mineset.owner")) {
                height = "00001";
                prefix = "§c§lOWNER §f";
            } else if (all.hasPermission("mineset.admin")) {
                height = "0003";
                prefix = "§c§lADMIN §f";
            } else if (all.hasPermission("mineset.mod")) {
                height = "0004";
                prefix = "§9§lMOD §f";
            } else if (all.hasPermission("mineset.helper")) {
                height = "0005";
                prefix = "§9§lHELPER §f";
            } else if (all.hasPermission("mineset.builder")) {
                height = "0006";
                prefix = "§5§lMAP TEAM §f";
            } else if (all.hasPermission("mineset.elite")) {
                height = "0070";
                prefix = "§a§lELITE §f";
            } else if (all.hasPermission("mineset.hero")) {
                height = "0080";
                prefix = "§e§lHERO §f";
            } else if (all.hasPermission("mineset.pro")) {
                height = "0090";
                prefix = "§b§lPRO §f";
            } else {
                height = "0100";
                prefix = "§7";
            }

            team = null;
            @SuppressWarnings("rawtypes")
            Iterator var12 = board.getTeams().iterator();

            while(var12.hasNext()) {
                Team team2 = (Team)var12.next();
                if (team2.getName().contains(all.getUniqueId().toString().replace("-", "").substring(0, 10))) {
                    team = team2;
                }
            }
        } while(team != null && team.getPrefix().equalsIgnoreCase(prefix));

        if (team != null) {
            team.unregister();
        }

        team = board.registerNewTeam(height + "-" + all.getUniqueId().toString().replace("-", "").substring(0, 10));
        team.setPrefix(prefix);
        team.addEntry(all.getName());
        team.addPlayer(all);
        all.setScoreboard((Scoreboard)this.scoreboard.get(all));
    }

    public void boardStart(final Player player) {
        this.taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.main, new Runnable() {
            public void run() {
                if (player.isOnline()) {
                    ScoreboardListener.this.boardStats(player);
                } else {
                    player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                    ScoreboardListener.this.scoreboard.remove(player);
                    Bukkit.getScheduler().cancelTask((Integer)ScoreboardListener.this.task.get(player));
                }

            }
        }, 0L, 20L);
        this.task.put(player, this.taskID);
    }
}

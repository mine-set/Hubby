package net.mineset.hubby.permissions;

import org.bukkit.entity.Player;

public class RankFinder {
    protected static InternalServerRank RankList[] = {
            // Staff ranks
            new InternalServerRank("OWNER", "mineset.owner", "§c", "§c§lOWNER §7", "§f", 1000),
            new InternalServerRank("ADMIN", "mineset.admin", "§c", "§c§lADMIN §7", "§f", 300),
            new InternalServerRank("MOD", "mineset.mod", "§9", "§9§lMOD §7", "§f", 200),
            new InternalServerRank("HELPER", "mineset.helper", "§9", "§9§lHELPER §7", "§f", 100),
            // Donor ranks
            new InternalServerRank("ELITE", "mineset.elite", "§a", "§a§lELITE §7", "§f", 75),
            new InternalServerRank("HERO", "mineset.hero", "§e", "§e§lHERO §7", "§f", 50),
            new InternalServerRank("PRO", "mineset.pro", "§b", "§b§lPRO §7", "§f", 25),
            // Default
            new InternalServerRank("REGULAR", "mineset.regular", "§7", "§7", "§7")
    };

    public static InternalServerRank GetRank(Player player) {
        InternalServerRank highestRank = new InternalServerRank();

        for (InternalServerRank rank : RankList) {
            if (rank.Weight > highestRank.Weight && (rank.Permission == "[NONE]" || player.hasPermission(rank.Permission))) {
                highestRank = rank;
            }
        }

        return highestRank;
    }
}

package net.mineset.hubby.permissions;

public class InternalServerRank {
    public String Name;
    public String Permission;

    public String ShortPrefix;
    public String Prefix;

    public String CColor;

    public int Weight = 0;

    InternalServerRank(String rankName, String serverPermission, String shortPrefix, String prefix, String chatColor) {
        Name = rankName;
        Permission = serverPermission;
        ShortPrefix = shortPrefix;
        Prefix = prefix;
        CColor = chatColor;
    }

    InternalServerRank(String rankName, String serverPermission, String shortPrefix, String prefix, String chatColor, int weight) {
        Name = rankName;
        Permission = serverPermission;
        ShortPrefix = shortPrefix;
        Prefix = prefix;
        CColor = chatColor;
        Weight = weight;
    }

    InternalServerRank() {
        Weight = -1;
    }
}
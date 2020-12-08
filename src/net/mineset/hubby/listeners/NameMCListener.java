package net.mineset.hubby.listeners;

import net.mineset.hubby.utils.DefaultFontInfo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class NameMCListener implements Listener {


    public static boolean hasVoted(String uuid) throws Exception {
        try (Scanner scanner = new Scanner(new URL("https://api.namemc.com/server/play.mineset.net/likes?profile=" + uuid)
                .openStream()).useDelimiter("\\A")) {
            return Boolean.parseBoolean(scanner.next());
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        CompletableFuture.supplyAsync(() -> {
            boolean voted = false;
            try {
                voted = hasVoted(player.getUniqueId().toString());
            } catch (Exception ignored) {}

            return voted;
        }).thenAccept(liked -> {
            if (liked) return;

            player.sendMessage("§d§lHub §7▪ §aSeems like you've not liked our NameMC!");
            player.sendMessage("§d§lHub §7▪ §aUpgrade your rank for free by liking it! §l§nClick Here!");
        });
    }

    private final static int CENTER_PX = 154;

    public static void sendCenteredMessage(Player player, String message) {
        if (message == null || message.equals("")) player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()) {
            if (c == '§'){
                previousCode = true;
                continue;
            } else if(previousCode == true) {
                previousCode = false;
                if(c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                } else isBold = false;
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }
}


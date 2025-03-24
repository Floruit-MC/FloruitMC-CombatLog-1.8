package com.hanielcota.combatlog.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

@Getter
public class CombatCache {

    private final Cache<Player, Long> combatTimes;
    private final long combatTimeout;

    public CombatCache(long combatTimeout, String outOfCombatMessage) {
        this.combatTimeout = combatTimeout;
        this.combatTimes = Caffeine.newBuilder()
                .expireAfterWrite(combatTimeout, TimeUnit.MILLISECONDS)
                .removalListener(createRemovalListener(outOfCombatMessage))
                .build();
    }

    private RemovalListener<Player, Long> createRemovalListener(String outOfCombatMessage) {
        return (player, timestamp, cause) -> notifyPlayerOnCombatEnd(player, outOfCombatMessage, cause);
    }

    private void notifyPlayerOnCombatEnd(Object playerObject, String message, RemovalCause cause) {
        if (playerObject == null) return;
        if (!(playerObject instanceof Player)) return;
        if (cause != RemovalCause.EXPIRED) return;

        Player player = (Player) playerObject;
        if (!player.isOnline()) return;
        if (message == null) return;

        player.sendMessage(message);
    }
}
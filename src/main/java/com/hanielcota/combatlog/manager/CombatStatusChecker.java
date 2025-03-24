package com.hanielcota.combatlog.manager;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class CombatStatusChecker {

    private final Cache<Player, Long> combatTimes;

    public boolean isNotInCombat(Player player) {
        if (player == null) {
            return true;
        }

        return combatTimes.getIfPresent(player) == null;
    }
}
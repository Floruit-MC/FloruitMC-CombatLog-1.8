package com.hanielcota.combatlog.manager;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@Getter
public class CombatManager {

    private final Cache<Player, Long> combatTimes;

    public void enterCombat(Player player) {
        if (player == null) {
            return;
        }

        combatTimes.put(player, System.currentTimeMillis());
    }

    public void removeFromCombat(Player player) {
        if (player == null) {
            return;
        }

        combatTimes.invalidate(player);
    }
}
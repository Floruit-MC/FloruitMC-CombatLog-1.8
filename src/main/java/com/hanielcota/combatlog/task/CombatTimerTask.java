package com.hanielcota.combatlog.task;

import com.hanielcota.combatlog.config.CombatConfig;
import com.hanielcota.combatlog.manager.CombatManager;
import com.hanielcota.combatlog.util.ActionBarUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class CombatTimerTask extends BukkitRunnable {

    private final CombatManager combatManager;
    private final CombatConfig combatConfig;

    @Override
    public void run() {
        if (combatManager == null || combatConfig == null) return;

        combatManager.getCombatTimes().asMap().forEach((player, combatTime) -> {
            if (player == null) return;
            if (!player.isOnline()) return;

            long timeElapsed = System.currentTimeMillis() - combatTime;
            long timeLeft = combatConfig.getCombatTimeout() - timeElapsed;
            if (timeLeft <= 0) return;

            ActionBarUtils.sendActionBar(player, "§dVocê está em combate: §d§l" + (timeLeft / 1000) + "§ds restantes");
        });
    }
}
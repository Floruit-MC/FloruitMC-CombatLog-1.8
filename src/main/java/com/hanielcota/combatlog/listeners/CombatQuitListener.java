package com.hanielcota.combatlog.listeners;

import com.hanielcota.combatlog.manager.CombatManager;
import com.hanielcota.combatlog.manager.CombatStatusChecker;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class CombatQuitListener implements Listener {

    private final CombatManager combatManager;
    private final CombatStatusChecker combatStatusChecker;

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (combatManager == null || combatStatusChecker == null) return;

        Player player = event.getPlayer();
        if (combatStatusChecker.isNotInCombat(player)) return;

        player.setHealth(0.0);
        player.getWorld().strikeLightningEffect(player.getLocation());
        combatManager.removeFromCombat(player);
    }
}
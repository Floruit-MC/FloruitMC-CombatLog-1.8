package com.hanielcota.combatlog.listeners;

import com.hanielcota.combatlog.config.CombatConfig;
import com.hanielcota.combatlog.manager.CombatManager;
import com.hanielcota.combatlog.manager.CombatStatusChecker;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@RequiredArgsConstructor
public class CombatCommandListener implements Listener {

    private final CombatManager combatManager;
    private final CombatStatusChecker combatStatusChecker;
    private final CombatConfig combatConfig;

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (combatManager == null || combatStatusChecker == null || combatConfig == null) return;

        Player player = event.getPlayer();
        if (combatStatusChecker.isNotInCombat(player)) return;
        if (player.hasPermission("combatlog.bypass")) return;

        event.setCancelled(true);
        player.sendMessage(combatConfig.getCommandBlockedMessage());
    }
}
package com.hanielcota.combatlog.listeners;

import com.hanielcota.combatlog.config.CombatConfig;
import com.hanielcota.combatlog.manager.CombatManager;
import com.hanielcota.combatlog.manager.CombatStatusChecker;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@RequiredArgsConstructor
public class CombatDamageListener implements Listener {

    private final CombatConfig combatConfig;
    private final CombatStatusChecker statusChecker;
    private final CombatManager combatManager;

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;

        Player target = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        notifyAndEnterCombat(target);
        notifyAndEnterCombat(damager);
    }

    private void notifyAndEnterCombat(Player player) {
        if (!statusChecker.isNotInCombat(player)) return;

        combatManager.enterCombat(player);
        player.sendMessage(combatConfig.getCombatMessage());
    }
}
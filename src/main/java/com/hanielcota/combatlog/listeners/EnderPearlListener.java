package com.hanielcota.combatlog.listeners;

import com.hanielcota.combatlog.manager.EnderPearlCooldownManager;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@AllArgsConstructor
public class EnderPearlListener implements Listener {

    private final EnderPearlCooldownManager cooldownManager;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!event.hasItem()) return;
        if (event.getItem().getType() != Material.ENDER_PEARL) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (cooldownManager.hasCooldown(player)) {
            long remainingTime = cooldownManager.getRemainingTime(player);
            player.sendMessage("§c§lFLORUIT MC §f➤ §cAguarde " + remainingTime + " segundos para usar a EnderPearl novamente!");
            event.setCancelled(true);
            player.updateInventory();
            return;
        }

        player.updateInventory();
        cooldownManager.applyCooldown(player);
    }
}
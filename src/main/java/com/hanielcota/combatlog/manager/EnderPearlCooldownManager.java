package com.hanielcota.combatlog.manager;

import com.hanielcota.combatlog.util.CooldownBuilder;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class EnderPearlCooldownManager {

    private final CooldownBuilder enderPearlCooldown;

    public EnderPearlCooldownManager() {
        enderPearlCooldown = CooldownBuilder.builder()
                .duration(16)
                .timeUnit(TimeUnit.SECONDS)
                .cooldownName("enderpearl")
                .build();
    }

    public boolean hasCooldown(Player player) {
        return enderPearlCooldown.hasCooldown(player);
    }

    public long getRemainingTime(Player player) {
        return enderPearlCooldown.getRemainingTime(player);
    }

    public void applyCooldown(Player player) {
        enderPearlCooldown.setCooldown(player);
    }
}
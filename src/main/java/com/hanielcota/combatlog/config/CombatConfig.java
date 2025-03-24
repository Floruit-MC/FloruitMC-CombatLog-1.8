package com.hanielcota.combatlog.config;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class CombatConfig {

    private final JavaPlugin plugin;
    private FileConfiguration config;
    private Long cachedCombatTimeout;
    private String cachedCombatMessage;
    private String cachedCommandBlockedMessage;
    private String cachedOutOfCombatMessage;

    public CombatConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        config = plugin.getConfig();

        cachedCombatTimeout = config.getLong("combat-timeout", 10000L);
        cachedCombatMessage = config.getString("combat-message", "§cVocê está em combate! Sair agora resultará em morte.");
        cachedCommandBlockedMessage = config.getString("command-blocked-message", "§cVocê não pode usar comandos em combate!");
        cachedOutOfCombatMessage = config.getString("out-of-combat-message", "§aVocê não está mais em combate.");
    }

    public long getCombatTimeout() {
        return cachedCombatTimeout;
    }

    public String getCombatMessage() {
        return cachedCombatMessage;
    }

    public String getCommandBlockedMessage() {
        return cachedCommandBlockedMessage;
    }

    public String getOutOfCombatMessage() {
        return cachedOutOfCombatMessage;
    }

    public void reload() {
        plugin.reloadConfig();
        loadConfig();
    }
}
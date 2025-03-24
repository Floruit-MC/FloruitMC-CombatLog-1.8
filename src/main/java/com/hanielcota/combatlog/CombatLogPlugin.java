package com.hanielcota.combatlog;

import co.aikar.commands.PaperCommandManager;
import com.hanielcota.combatlog.cache.CombatCache;
import com.hanielcota.combatlog.commands.CombatLogCommand;
import com.hanielcota.combatlog.config.CombatConfig;
import com.hanielcota.combatlog.listeners.CombatCommandListener;
import com.hanielcota.combatlog.listeners.CombatDamageListener;
import com.hanielcota.combatlog.listeners.CombatQuitListener;
import com.hanielcota.combatlog.listeners.EnderPearlListener;
import com.hanielcota.combatlog.manager.CombatManager;
import com.hanielcota.combatlog.manager.CombatStatusChecker;
import com.hanielcota.combatlog.manager.EnderPearlCooldownManager;
import com.hanielcota.combatlog.task.CombatTimerTask;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CombatLogPlugin extends JavaPlugin {

    private CombatManager combatManager;
    private CombatStatusChecker combatStatusChecker;
    private CombatConfig combatConfig;
    private EnderPearlCooldownManager enderPearlCooldownManager;

    @Override
    public void onEnable() {
        initializeConfig();

        initializeManagers();
        registerListeners();
        registerCommands();
        startTasks();

        getServer().getConsoleSender().sendMessage("§eFloruitCombatLog ativado com sucesso!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§cFloruitCombatLog desativado com sucesso!");
    }

    private void initializeConfig() {
        saveDefaultConfig();
        combatConfig = new CombatConfig(this);
    }

    private void initializeManagers() {
        CombatCache combatCache =
                new CombatCache(combatConfig.getCombatTimeout(), combatConfig.getOutOfCombatMessage());

        combatManager = new CombatManager(combatCache.getCombatTimes());
        combatStatusChecker = new CombatStatusChecker(combatCache.getCombatTimes());
        enderPearlCooldownManager = new EnderPearlCooldownManager();
    }

    private void registerCommands() {
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new CombatLogCommand(combatManager, combatConfig));
    }

    private void registerListeners() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new CombatDamageListener(combatConfig, combatStatusChecker, combatManager), this);
        pluginManager.registerEvents(new CombatQuitListener(combatManager, combatStatusChecker), this);
        pluginManager.registerEvents(new CombatCommandListener(combatManager, combatStatusChecker, combatConfig), this);
        pluginManager.registerEvents(new EnderPearlListener(enderPearlCooldownManager), this);
    }

    private void startTasks() {
        new CombatTimerTask(combatManager, combatConfig).runTaskTimerAsynchronously(this, 20L, 20L);
    }
}
package com.hanielcota.combatlog.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.hanielcota.combatlog.config.CombatConfig;
import com.hanielcota.combatlog.manager.CombatManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("combatlog|cl")
@RequiredArgsConstructor
public class CombatLogCommand extends BaseCommand {

    private final CombatManager combatManager;
    private final CombatConfig combatConfig;

    @Subcommand("status")
    @CommandPermission("combatlog.status")
    @CommandCompletion("@players")
    @Description("Mostra o estado de combate de um jogador.")
    public void onStatus(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("§c§lFLORUIT MC §f➤ §c✘ Uso: /combatlog status <jogador>");
            return;
        }

        if (args.length > 1) {
            sender.sendMessage("§c§lFLORUIT MC §f➤ §c✘ Muitos argumentos! Uso: /combatlog status <jogador>");
            return;
        }

        Player target = sender.getServer().getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§c§lFLORUIT MC §f➤ §c✘ Jogador '" + args[0] + "' não encontrado!");
            return;
        }

        Long combatTime = combatManager.getCombatTimes().getIfPresent(target);
        if (combatTime == null) {
            sender.sendMessage("§e§lFLORUIT MC §f➤ §e✘ O jogador " + target.getName() + " não está em combate.");
            return;
        }

        long timeLeft = combatConfig.getCombatTimeout() - (System.currentTimeMillis() - combatTime);
        sender.sendMessage("§e§lFLORUIT MC §f➤ §e✔ O jogador " + target.getName() + " está em combate. Tempo restante: " + (timeLeft / 1000) + "s.");
    }

    @Subcommand("reload")
    @CommandPermission("combatlog.admin")
    @Description("Recarrega a configuração do CombatLog.")
    public void onReload(CommandSender sender, String[] args) {
        if (combatConfig == null) {
            sender.sendMessage("§c§lFLORUIT MC §f➤ §c✘ Erro interno: Configuração não inicializada.");
            return;
        }
        combatConfig.reload();
        sender.sendMessage("§a§lFLORUIT MC §f➤ §a✔ Configuração do CombatLog recarregada com sucesso!");
    }

    @Default
    @HelpCommand
    @Description("Mostra os comandos disponíveis.")
    public void onHelp(CommandSender sender, String[] args) {
        sender.sendMessage("§e§lFLORUIT MC §f➤ §e✔ Comandos disponíveis:");
        sender.sendMessage("§7/combatlog status <jogador> §f- Verifica o estado de combate.");
        sender.sendMessage("§7/combatlog reload §f- Recarrega a configuração.");
    }
}

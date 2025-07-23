---

# ⚔️ Floruit CombatLog – Sistema Anti-Logout em PvP

O **Floruit CombatLog** é um plugin projetado para evitar abusos no PvP, impedindo que jogadores desconectem para escapar de combates. Ele aplica um **tempo de combate ativo**, durante o qual certas ações são bloqueadas. O sistema também fornece feedback visual, sonoro e comandos administrativos para controle e monitoramento em tempo real.

---

## 💬 Comandos Disponíveis

### 👤 Comandos para Jogadores

| Comando                       | Descrição                                         | Permissão          |
| ----------------------------- | ------------------------------------------------- | ------------------ |
| `/combatlog`                  | Exibe ajuda e informações básicas sobre o sistema | *Acesso livre*     |
| `/combatlog status <jogador>` | Mostra o tempo restante de combate de um jogador  | `combatlog.status` |

### 🛠️ Comandos para Administradores

| Comando             | Descrição                                        | Permissão         |
| ------------------- | ------------------------------------------------ | ----------------- |
| `/combatlog reload` | Recarrega a configuração do plugin em tempo real | `combatlog.admin` |

---

## ⚙️ Funcionamento do Sistema

* 🔥 Ao entrar em combate, o jogador é automaticamente adicionado a um **temporizador de combate ativo**.
* ⏱️ O tempo é controlado por um gerenciador interno (`CombatManager`) e configurado em `CombatConfig`.
* 🚫 Durante esse tempo, ações como **sair do jogo, usar comandos, voar ou teleportar** podem ser **bloqueadas** ou **penalizadas**, conforme definido nas configurações.
* 📊 A equipe do servidor pode verificar o status de combate dos jogadores usando `/combatlog status`.

---

## ✅ Funcionalidades

* ⏳ **Temporizador de combate configurável** (em segundos)
* 🔔 **Alertas visuais e sonoros** durante o combate
* 🔄 **Recarregamento dinâmico da configuração** via comando
* 🧠 **Uso de cache inteligente** para melhor desempenho (`getIfPresent`)
* 🔌 **Integração com plugins de permissão** como LuckPerms

---

## 📂 Arquivo de Configuração (`combatlog.yml`)

> A configuração pode incluir:

```yaml
combat-time-seconds: 15
block-commands-during-combat: true
block-teleport: true
send-actionbar: true
play-sound-on-tag: true
```

---

## 📌 Requisitos

* Compatível com servidores **Paper 1.16+** ou superiores
* Recomendado o uso com **LuckPerms** ou outro gerenciador de permissões
* Ideal para servidores **Survival PvP**, **Factions**, **RPG** ou qualquer modo com combate entre jogadores


# ⚔️ Floruit CombatLog - Sistema de Combate PvP

O plugin **CombatLog** da Floruit impede que jogadores abusem do logout em combate. Ele gerencia um **tempo de combate ativo**, bloqueando ações e exibindo avisos visuais e sonoros. Também inclui comandos administrativos para verificação de status e recarregamento da configuração.

---

## 💬 Comandos

### 👀 Comandos Gerais

| Comando                               | Função                                                   | Permissão             |
|--------------------------------------|----------------------------------------------------------|-----------------------|
| `/combatlog`                         | Mostra a ajuda do comando                                | _Sem permissão_       |
| `/combatlog status <jogador>`        | Mostra o tempo restante de combate de um jogador         | `combatlog.status`    |

### 🛠️ Comandos de Staff

| Comando                               | Função                                                   | Permissão             |
|--------------------------------------|----------------------------------------------------------|-----------------------|
| `/combatlog reload`                  | Recarrega o arquivo de configuração do plugin            | `combatlog.admin`     |

---

## ⚙️ Como Funciona

- Sempre que um jogador entra em combate, é adicionado a um **temporizador interno**.
- O tempo de combate é controlado por `CombatManager` e definido via `CombatConfig`.
- Ao tentar sair ou realizar ações específicas, jogadores com status de combate ativo poderão ser **bloqueados ou penalizados**, conforme configuração.
- Staffs podem verificar o tempo restante de combate com `/combatlog status`.

---

## ✅ Recursos

- 📜 Sistema de verificação do status de combate em tempo real
- 🔄 Comando para recarregar a configuração sem reiniciar o servidor
- ⏳ Temporizador configurável
- 🧠 Cache inteligente com `getIfPresent` para desempenho

---

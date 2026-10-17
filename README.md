# 💣 Minesweeper (Campo Minado)

**[🇧🇷 Português](#-português) · [🇬🇧 English](#-english)**

![Java](https://img.shields.io/badge/Java-17+-orange?logo=openjdk)
![JUnit 5](https://img.shields.io/badge/Tests-JUnit%205-25A162?logo=junit5&logoColor=white)
![No dependencies](https://img.shields.io/badge/dependências-nenhuma-64748B)

---

## 🇧🇷 Português

Campo Minado jogado no terminal, escrito em **Java puro** e sem dependências. O objetivo do projeto foi praticar **orientação a objetos**, **Streams**, **recursão** e **testes unitários** com um problema pequeno e bem definido.

```
 1  1  1  ?  ?  ?
 1  x  1  ?  ?  ?
 1  1  1  ?  ?  ?
          1  ?  ?
          1  ?  ?
          1  ?  ?

Digite (x, y): 3, 4
1 -> Abrir Campo ou 2 -> (Des)Marcar Campo
```

### Como jogar

| Símbolo | Significado |
|---|---|
| `?` | Campo fechado |
| `x` | Campo marcado (suspeita de mina) |
| `1`–`8` | Quantidade de minas nos campos vizinhos |
| *(vazio)* | Campo aberto sem minas por perto |
| `*` | Mina (aparece quando você perde) |

1. Digite a coordenada como `linha, coluna` (começando em `0`), por exemplo `2, 3`.
2. Escolha `1` para **abrir** o campo ou `2` para **marcar/desmarcar**.
3. Você **ganha** quando todos os campos sem mina estão abertos e todas as minas estão marcadas. Você **perde** se abrir uma mina.
4. Digite `exit` a qualquer momento para sair.

### Como executar

Requer **Java 17 ou superior**.

```bash
git clone https://github.com/Thiago-Bilangieri/minesweeper.git
cd minesweeper

javac -d out $(find src -name "*.java")
java -cp out br.com.bilangieri.minesweeper.Application
```

O tabuleiro padrão tem **6×6 com 2 minas**. Para mudar, altere `new Board(linhas, colunas, minas)` em `Application.java`.

> O projeto também pode ser aberto direto no **Eclipse** ou no **IntelliJ** (arquivos `.project` e `.classpath` incluídos).

### Como funciona

```
src/br/com/bilangieri/minesweeper
├── Application.java          # Ponto de entrada
├── model/
│   ├── Board.java            # Tabuleiro: cria os campos, liga os vizinhos e sorteia as minas
│   └── Field.java            # Campo: estado (aberto, marcado, minado) e regras de abertura
├── view/
│   └── ConsoleBoard.java     # Loop do jogo e leitura da entrada no terminal
└── exception/
    ├── ExplosionException.java  # Lançada ao abrir uma mina
    └── CloseException.java      # Lançada quando o jogador sai
```

- **Separação entre regra e interface**: `model` não sabe nada sobre o terminal. A `view` só lê comandos e imprime o tabuleiro, então daria para trocar por uma interface gráfica sem mexer nas regras.
- **Vizinhança calculada uma vez**: ao criar o tabuleiro, cada campo guarda a lista dos seus até 8 vizinhos (incluindo diagonais).
- **Abertura em cascata com recursão**: ao abrir um campo sem minas por perto, `Field.open()` abre os vizinhos automaticamente, como no jogo original.
- **Fim de jogo por exceção**: abrir uma mina lança `ExplosionException`, que o tabuleiro captura para revelar todas as minas antes de repassar para a `view`.

### Testes

Os testes unitários de `Field` (em `test/`) cobrem o cálculo de vizinhança (incluindo diagonais) e a marcação de campos. Rode pela sua IDE com **JUnit 5**.

---

## 🇬🇧 English

Terminal **Minesweeper** written in **plain Java** with no dependencies. The goal was to practice **object-oriented design**, **Streams**, **recursion** and **unit testing** on a small, well-defined problem.

### How to play

1. Type a coordinate as `row, column` (zero-based), e.g. `2, 3`.
2. Choose `1` to **open** the cell or `2` to **flag/unflag** it.
3. You **win** when every safe cell is open and every mine is flagged. Opening a mine **loses** the game.
4. Type `exit` at any time to quit.

Symbols: `?` closed · `x` flagged · `1`–`8` adjacent mines · blank = open and safe · `*` mine.

### Running

Requires **Java 17+**.

```bash
javac -d out $(find src -name "*.java")
java -cp out br.com.bilangieri.minesweeper.Application
```

The default board is **6×6 with 2 mines**. Change `new Board(rows, cols, mines)` in `Application.java`.

### Design highlights

- **Game rules are independent of the UI**: `model` knows nothing about the console, so the `view` could be swapped for a GUI.
- **Neighbours are computed once** when the board is built (up to 8 per cell, diagonals included).
- **Recursive flood fill**: opening a cell with no adjacent mines opens its neighbours automatically.
- **Game over through exceptions**: `ExplosionException` lets the board reveal every mine before the view reports the loss.
- **Unit tests** with JUnit 5 cover neighbour detection and flagging in `Field`.

---

## 👨‍💻 Autor / Author

**Thiago Bilangieri** · [LinkedIn](https://www.linkedin.com/in/thiagobilangieri/) · [GitHub](https://github.com/Thiago-Bilangieri)

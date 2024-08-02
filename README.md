# Jogo de Xadrez em Java

## Visão Geral
Bem-vindo ao projeto do Jogo de Xadrez! Este projeto implementa um jogo de xadrez totalmente funcional usando Java, aderindo às melhores práticas de Programação Orientada a Objetos (POO). O design da aplicação é estruturado seguindo um diagrama UML detalhado, garantindo clareza e manutenibilidade do código.

## Funcionalidades
- Implementação completa das regras do Xadrez
- Suporte para modo de dois jogadores
- Validação de movimentos e detecção de xeque-mate

## Design UML
![Diagrama UML](/img/chess-system-design.png)

O diagrama UML acima fornece uma visão geral de alto nível da arquitetura da aplicação, detalhando as classes e seus relacionamentos.

## Primeiros Passos
### Pré-requisitos
- Kit de Desenvolvimento Java (JDK) 8 ou superior
- Git

### Instalação
1. Clone o repositório:
    ```bash
    git clone https://github.com/viniciuspiotto/chess.git
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd chess
    ```

## Estrutura do Projeto

```bash
chess-game-java/
├── chess.iml
├── img
│   └── chess-system-design.png
├── README.md
└── src
    ├── application
    │   ├── Program.java
    │   └── UI.java
    ├── boardgame
    │   ├── BoardException.java
    │   ├── Board.java
    │   ├── Piece.java
    │   └── Position.java
    └── chess
        ├── ChessException.java
        ├── ChessMatch.java
        ├── ChessPiece.java
        ├── ChessPosition.java
        ├── Color.java
        └── pieces
            ├── Bishop.java
            ├── King.java
            ├── Knight.java
            ├── Pawn.java
            ├── Queen.java
            └── Rook.java
```

## Contato
Para qualquer dúvida ou feedback, por favor, abra uma issue ou entre em contato comigo no [LinkedIn](https://br.linkedin.com/in/viniciushpiotto).

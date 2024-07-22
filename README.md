# Chess Game in Java

## Overview
Welcome to the Chess Game project! This project implements a fully functional Chess game using Java, adhering to the best practices of Object-Oriented Programming (OOP). The design of the application is structured following a detailed UML diagram, ensuring clarity and maintainability of the code.

## Features
- Full implementation of Chess rules
- Support for two-player mode
- Move validation and checkmate detection

## UML Design
![UML Diagram](/img/chess-system-design.png)

The above UML diagram provides a high-level overview of the application's architecture, detailing the classes and their relationships.

## Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Git

### Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/viniciuspiotto/chess.git
    ```
2. Navigate to the project directory:
    ```bash
    cd chess
    ```

## Project Structure

```bash
chess-game-java/
├── chess.iml
├── img
│   └── chess-system-design.png
├── README.md
└── src
    ├── application
    │   ├── Program.java
    │   └── UI.java
    ├── boardgame
    │   ├── BoardException.java
    │   ├── Board.java
    │   ├── Piece.java
    │   └── Position.java
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

## Contact
For any questions or feedback, please open an issue or contact me in [Linkedin](https://br.linkedin.com/in/viniciushpiotto).
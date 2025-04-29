# ChessBot - Intelligent Chess Game with AI

An advanced chess game with integrated AI, developed in Java. The project combines classical chess with modern algorithms for a challenging gaming experience.

## Key Features

- **Complete Chess Engine**
  - Implementation of all chess rules and move possibilities
  - Detection of check, checkmate, and stalemate positions
  - Special moves including castling and en passant
  - Comprehensive move validation system

- **Advanced AI**
  - Sophisticated bot system with multiple difficulty levels
  - Implemented evaluation functions for game positions
  - Strategic move calculation through the backend engine
  - Position analysis and move prediction

- **User Interface**
  - Graphical representation of the chess board
  - Intuitive move execution
  - Visual indication of possible moves
  - Clean and modern design

## Technical Architecture

The project is structured into several main components:

- **Backend (`org.example.chess.backend`)**
  - `board`: Game logic and board state implementation
  - `bot`: AI logic and strategy calculation
  - `controller`: Game flow control and move management
  - `figures`: Chess piece definition and behavior
  - `evaluate`: Position evaluation and analysis functions
  - `util`: Utility functions and helper classes

- **Frontend (`org.example.chess.frontend`)**
  - Graphical user interface
  - Game visualization
  - User interaction handling
  - Event management

## Installation

1. Ensure Java is installed (minimum version 8)
2. Clone the repository:
```bash
git clone [repository-url]
```
3. Navigate to the project directory:
```bash
cd ChessBot-Refactoring
```
4. Build the project using Maven:
```bash
mvn clean install
```
5. Launch the application:
```bash
java -jar target/Chess.jar
```

## Planned Features

- Implementation of various AI difficulty levels
- Save and load game states
- Online multiplayer mode
- Integration of chess opening libraries
- Performance optimizations
- Advanced game analytics

## Technical Requirements

- Java 8 or higher
- Maven for dependency management
- Minimum 4GB RAM recommended
- Graphics card supporting JavaFX

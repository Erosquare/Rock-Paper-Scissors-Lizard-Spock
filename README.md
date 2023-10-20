# Rock, Paper, Scissors, Lizard, Spock CLI implementation in Java

This application allows to play the game Rock, Paper, Scissors, Lizard, Spock in the command line. The player is playing
against the computer. The computer is choosing a random move. The player can choose between 5 moves: Rock, Paper, Scissors, Lizard, Spock.

The rules of the game are the following:
- Rock beats Scissors and Lizard
- Paper beats Rock and Spock
- Scissors beats Paper and Lizard
- Lizard beats Paper and Spock
- Spock beats Rock and Scissors


## Requirements
To run and compile this project, you need the following prerequisites:

- OpenJDK 11
- Maven 3.x

## Compile the application
To clean and compile the application, go at the root folder of the project the type the following command:
```bash
mvn clean compile
```

## Run the game
To run the game, go at the root folder of the project the type the following command:
```bash
mvn exec:java
```

## Run the Unit Tests
To run the unit tests, type the following command after compiling:
```bash
mvn test
``` 
Or to compile and run the tests at once:
```bash
mvn clean compile test
``` 

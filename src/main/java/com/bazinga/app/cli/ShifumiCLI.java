package com.bazinga.app.cli;

import com.bazinga.game.AbstractShifumiGame;
import com.bazinga.game.ShifumiGameEngine;
import com.bazinga.model.Player;
import com.bazinga.model.ShapeEnum;

import java.io.Console;
import java.util.Optional;
import java.util.Scanner;

/**
 * The command line interface of the Shifumi game
 */
public class ShifumiCLI extends AbstractShifumiGame {

    /**
     * Create a new CLI Shifumi game
     *
     * @param engine  The game engine
     * @param player1 The first player
     * @param player2 The second player
     */
    public ShifumiCLI(ShifumiGameEngine engine, Player player1, Player player2) {
        super(engine, player1, player2);
    }

    @Override
    protected void run() {
        if (engine == null || player1 == null || player2 == null) {
            throw new IllegalStateException("The game is not properly initialized");
        }

        printWelcomeMessage();
        System.out.print("\n");

        printRules();
        System.out.print("\n");

        // Ask the player to choose a shape from the command line
        printChoicesToPlayer(player1);
        Optional<ShapeEnum> chosenShape = Optional.empty();
        Scanner in = new Scanner(System.in);

        while (chosenShape.isEmpty()) {
            try {
                int choice = Integer.parseInt(in.nextLine());
                chosenShape = getShapeFromChoice(choice);

                if (chosenShape.isPresent()) {
                    player1.playShape(chosenShape.get());
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 1 and 5");
            }
        }

        // The computer chooses a random shape
        player2.playShape(ShapeEnum.values()[(int) (Math.random() * ShapeEnum.values().length)]);

        // Print the shapes played by the players
        printShapePlayedByPlayer(player1);
        printShapePlayedByPlayer(player2);

        // Get the winner of the round
        Optional<Player> winner = engine.play(player1, player2);
        printWinner(winner);
        System.out.print("\n");

        // Print the scores of the players
        printScoreOfPlayer(player1);
        printScoreOfPlayer(player2);
        System.out.print("\n");

        // Continue the game?
        String answer = "";
        do {
            System.out.println("Do you want to continue? (y/n)");
            answer = in.nextLine();
        } while (!answer.equals("y") && !answer.equals("n"));

        if (answer.equals("n")) {
            isRunning = false;
        } else {
            Console console = System.console();
            // console is null in IDEs
            if (console != null) {
                console.flush();
            }
        }
    }

    void printWinner(Optional<Player> winner) {
        // Print the winner of the round
        winner.ifPresentOrElse(
                player -> {
                    System.out.println(player.getName() + " won the round! Bazinga!");
                },
                () -> System.out.println("It's a draw!")
        );
    }

    /**
     * Print the welcome message of the game
     */
     void printWelcomeMessage() {
        System.out.println("Welcome to Rock Paper Scissors Lizard Spock!\n");
        System.out.println("The rules are simple: each player chooses a shape and the winner is determined by the rules of the game.");
        System.out.println("The game is played in rounds. Each round, the players choose a shape and the winner of the round gets a point.");
        System.out.println("You are playing against the computer.");
        System.out.println("Good luck!");
    }

    /**
     * Print the rules of the game
     */
    public void printRules() {
        System.out.println("The rules of the game are:");
        engine.getRules().forEach(System.out::println);
    }

    /**
     * Print the choices of shapes to the player
     *
     * @param player The player to print the choices to
     */
    void printChoicesToPlayer(Player player) {
        System.out.println(player.getName() + ", please choose a shape:");
        System.out.println("1. Rock");
        System.out.println("2. Paper");
        System.out.println("3. Scissors");
        System.out.println("4. Lizard");
        System.out.println("5. Spock");
        System.out.print("Your choice: ");
    }

    /**
     * Print the shape played by a player
     *
     * @param player The player to print the shape played by
     */
    void printShapePlayedByPlayer(Player player) {
        System.out.println(player.getName() + " played " + player.getShape().get());
    }

     void printScoreOfPlayer(Player player) {
        System.out.println(player.getName() + " has " + player.getScore() + " points.");
    }

    /**
     * Get the shape chosen by the player from the command line
     *
     * @param choice The choice of the player
     * @return The shape chosen by the player, or empty if the choice is invalid
     */
     Optional<ShapeEnum> getShapeFromChoice(int choice) throws NumberFormatException {
        Optional<ShapeEnum> chosenShape;
        switch (choice) {
            case 1:
                chosenShape = Optional.of(ShapeEnum.ROCK);
                break;
            case 2:
                chosenShape = Optional.of(ShapeEnum.PAPER);
                break;
            case 3:
                chosenShape = Optional.of(ShapeEnum.SCISSORS);
                break;
            case 4:
                chosenShape = Optional.of(ShapeEnum.LIZARD);
                break;
            case 5:
                chosenShape = Optional.of(ShapeEnum.SPOCK);
                break;
            default:
                chosenShape = Optional.empty();
        }
        return chosenShape;
    }


}

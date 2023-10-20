package com.bazinga.game;

import com.bazinga.model.Player;

/**
 *  Abstract representation of a Shifumi game. A Shifumi game is composed of a game engine and two players.
 *  The game engine is responsible for the rules of the game and the players are responsible for their moves.
 *  The course of the game implementation is left to the subclasses.
 */
public abstract class AbstractShifumiGame {

    protected ShifumiGameEngine engine; // The game engine

    protected Player player1; // The first player

    protected Player player2; // The second player

    protected boolean isRunning; // Boolean asserting if the game is running or not

    /**
     * Create a new Shifumi game
     *
     * @param engine The game engine
     * @param player1 The first player
     * @param player2 The second player
     */
    public AbstractShifumiGame(ShifumiGameEngine engine, Player player1, Player player2) {
        this.engine = engine;
        this.player1 = player1;
        this.player2 = player2;
        this.isRunning = false;
    }

    /**
     * Start the game and run it until it ends
     */
    public final void start() {
        isRunning = true;
        while (isRunning) {
            run();
        }
    }

    /**
     * Run the game. This method needs to be overridden by the subclasses.
     */
    protected abstract void run();
}

package com.bazinga.model;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


/**
 * A player of the game. A player is composed of a unique identifier, a name, a score and a shape. The shape is the
 * shape played by the player during a round of the game. The score is the number of rounds won by the player.
 * The player is identified by its unique identifier. The name is used to display the player in the game.
 */
public class Player {
    private UUID playerId; // Unique identifier of the player
    private String name; // Name of the player
    private int score; // Score of the player
    private ShapeEnum shape; // Shape played by the player

    /**
     * Create a new player
     *
     * @param name The name of the player
     */
    public Player(String name) {
        this.playerId = UUID.randomUUID();
        this.name = (name == null || name.isBlank())? "" : name;
        this.score = 0;
        this.shape = null;
    }

    /**
     * Get the player's name
     *
     * @return The name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Get the player's shape
     *
     * @return The shape of the player
     */
    public Optional<ShapeEnum> getShape() {
        return Optional.ofNullable(shape);
    }

    /**
     * Get the player's score
     *
     * @return The score of the player
     */
    public int getScore() {
        return score;
    }

    /**
     * Increment the player's score
     */
    public void incrementScore() {
        score++;
    }

    /**
     * Play a shape
     *
     * @param shape The shape to play
     */
    public void playShape(ShapeEnum shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerId, player.playerId);
    }

    @Override
    public int hashCode() {
        return playerId.hashCode();
    }
}

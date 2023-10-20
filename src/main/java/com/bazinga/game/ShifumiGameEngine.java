package com.bazinga.game;

import com.bazinga.model.Player;
import com.bazinga.model.Rule;
import com.bazinga.model.ShapeEnum;

import java.util.*;


/**
 * A generic Shifumi game engine. Allows to play Shifumi between two players according to the personalized rules added
 * to the engine.
 */
public enum ShifumiGameEngine {
    INSTANCE; // Thread-safe singleton instance

    private Map<Rule, String> rulesMap = Collections.synchronizedMap(new LinkedHashMap<>()); // Ordered map of rules of the game

    /**
     * Add a rule to the game
     *
     * @param gameRule The rule to add
     */
    public void addRule(Rule gameRule) {
        rulesMap.put(gameRule, gameRule.toString());
    }

    /**
     * Get the rules descriptions of the game
     *
     * @return The list of the rules descriptions of the game
     */
    public Collection<String> getRules() {
        return rulesMap.values();
    }

    /**
     * Clear the rules of the game
     */
    public void clearRules() {
        rulesMap.clear();
    }

    /**
     * Play a round of the game
     *
     * @param player The first player
     * @param opponent The second player
     * @return The winner of the round, or empty if it's a draw
     */
    public Optional<Player> play(Player player, Player opponent) {
        if (player != null && opponent != null
        && player.getShape().isPresent() && opponent.getShape().isPresent()) {
            ShapeEnum playerShape = player.getShape().get();
            ShapeEnum opponentShape = opponent.getShape().get();

            // Same shape, it's a draw
            if (playerShape == opponentShape) {
                return Optional.empty();
            }

            // Check if the player wins with his shape against the opponent shape
            if (rulesMap.containsKey(new Rule(playerShape, opponentShape))) {
                player.incrementScore();
                return Optional.of(player);
            // If not, check if the opponent wins with his shape against the player shape
            } else if (rulesMap.containsKey(new Rule(opponentShape, playerShape))) {
                opponent.incrementScore();
                return Optional.of(opponent);
            }
        }
        // If no one wins, it's a draw
        return Optional.empty();
    }
}

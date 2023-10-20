package com.bazinga.game;

import com.bazinga.model.Player;
import com.bazinga.model.Rule;
import com.bazinga.model.ShapeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Unit tests for {@link ShifumiGameEngine} class
 */
public class ShifumiGameEngineTest {

    @BeforeEach
    public void setUp() {
        ShifumiGameEngine.INSTANCE.clearRules();
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check addRule()")
    public void testShifumiGameEngine_addRule() {
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS));

        Assertions.assertEquals(1, ShifumiGameEngine.INSTANCE.getRules().size());
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check getRules()")
    public void testShifumiGameEngine_getRules() {
        Assertions.assertEquals(0, ShifumiGameEngine.INSTANCE.getRules().size());
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check getRules() ordering with rules and duplicates")
    public void testShifumiGameEngine_getRulesWithRulesAndDuplicates() {
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS));
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.LIZARD));
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.PAPER, "covers", ShapeEnum.ROCK));
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.PAPER, "covers", ShapeEnum.ROCK));

        // Expected rules order: ROCK crushes SCISSORS, ROCK crushes LIZARD, PAPER covers ROCK
        List<String> expectedRules = List.of("ROCK crushes SCISSORS", "ROCK crushes LIZARD", "PAPER covers ROCK");

        List<String> rules = new ArrayList<>(ShifumiGameEngine.INSTANCE.getRules());

        Assertions.assertEquals(expectedRules, rules);
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check play() with one rule. Player wins.")
    public void testShifumiGameEngine_playWithOneRule() {
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS));
        Player player = new Player("Player 1");
        Player opponent = new Player("Player 2");

        // Player plays ROCK, Opponent plays SCISSORS
        player.playShape(ShapeEnum.ROCK);
        opponent.playShape(ShapeEnum.SCISSORS);
        ShifumiGameEngine.INSTANCE.play(player, opponent);

        Assertions.assertEquals(1, player.getScore());
        Assertions.assertEquals(0, opponent.getScore());
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check play() with one rule. Opponent wins.")
    public void testShifumiGameEngine_playWithOneRuleOpponentWins() {
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS));
        Player player = new Player("Player 1");
        Player opponent = new Player("Player 2");

        // Player plays SCISSORS, Opponent plays ROCK
        player.playShape(ShapeEnum.SCISSORS);
        opponent.playShape(ShapeEnum.ROCK);
        ShifumiGameEngine.INSTANCE.play(opponent, player);

        Assertions.assertEquals(0, player.getScore());
        Assertions.assertEquals(1, opponent.getScore());
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check play() with one rule. Draw.")
    public void testShifumiGameEngine_playWithOneRuleDraw() {
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS));
        Player player = new Player("Player 1");
        Player opponent = new Player("Player 2");

        // Player plays ROCK, Opponent plays ROCK
        player.playShape(ShapeEnum.ROCK);
        opponent.playShape(ShapeEnum.ROCK);
        // Draw, winner is empty
        Optional<Player> winner = ShifumiGameEngine.INSTANCE.play(player, opponent);

        Assertions.assertTrue(winner.isEmpty());
        Assertions.assertEquals(0, player.getScore());
        Assertions.assertEquals(0, opponent.getScore());
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check play() with one rule. Unknown move (rule) played. Draw.")
    public void testShifumiGameEngine_playWithOneRuleAndUnknownMoveDraw() {
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS));
        Player player = new Player("Player 1");
        Player opponent = new Player("Player 2");

        // Player plays SPOCK, Opponent plays PAPER
        player.playShape(ShapeEnum.SPOCK);
        opponent.playShape(ShapeEnum.PAPER);
        // Draw, winner is empty
        Optional<Player> winner = ShifumiGameEngine.INSTANCE.play(player, opponent);

        Assertions.assertTrue(winner.isEmpty());
        Assertions.assertEquals(0, player.getScore());
        Assertions.assertEquals(0, opponent.getScore());
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check play() with null player.")
    public void testShifumiGameEngine_playWithNullPlayer() {
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS));
        Player opponent = new Player("Player 2");

        // Player plays ROCK, Opponent plays SCISSORS
        opponent.playShape(ShapeEnum.SCISSORS);
        Optional<Player> winner = ShifumiGameEngine.INSTANCE.play(null, opponent);

        Assertions.assertTrue(winner.isEmpty());
        Assertions.assertEquals(0, opponent.getScore());
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check play() with null opponent.")
    public void testShifumiGameEngine_playWithNullOpponent() {
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS));
        Player player = new Player("Player 1");

        // Player plays ROCK, Opponent plays SCISSORS
        player.playShape(ShapeEnum.ROCK);
        Optional<Player> winner = ShifumiGameEngine.INSTANCE.play(player, null);

        Assertions.assertTrue(winner.isEmpty());
        Assertions.assertEquals(0, player.getScore());
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check play() while player not playing a shape.")
    public void testShifumiGameEngine_playWithPlayerNotPlayingAShape(){
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS));
        Player player = new Player("Player 1");
        Player opponent = new Player("Player 2");

        // Player plays null, Opponent plays SCISSORS
        opponent.playShape(ShapeEnum.SCISSORS);
        Optional<Player> winner = ShifumiGameEngine.INSTANCE.play(player, opponent);

        Assertions.assertTrue(winner.isEmpty());
        Assertions.assertEquals(0, player.getScore());
        Assertions.assertEquals(0, opponent.getScore());
    }

    @Test
    @DisplayName("Test ShifumiGameEngine - Check play() while opponent not playing a shape.")
    public void testShifumiGameEngine_playWithOpponentNotPlayingAShape(){
        ShifumiGameEngine.INSTANCE.addRule(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS));
        Player player = new Player("Player 1");
        Player opponent = new Player("Player 2");

        // Player plays ROCK, Opponent plays null
        player.playShape(ShapeEnum.ROCK);
        Optional<Player> winner = ShifumiGameEngine.INSTANCE.play(player, opponent);

        Assertions.assertTrue(winner.isEmpty());
        Assertions.assertEquals(0, player.getScore());
        Assertions.assertEquals(0, opponent.getScore());
    }

}

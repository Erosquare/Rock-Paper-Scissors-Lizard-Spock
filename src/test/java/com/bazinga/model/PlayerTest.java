package com.bazinga.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Player} class
 */
public class PlayerTest {

    @Test
    @DisplayName("Test Player - Check constructor with null name")
    public void testPlayer_constructorWithNullName() {
        Player player = new Player(null);

        Assertions.assertEquals("", player.getName());
    }

    @Test
    @DisplayName("Test Player - Check getName()")
    public void testPlayer_getName() {
        Player player = new Player("Player 1");

        Assertions.assertEquals("Player 1", player.getName());
    }

    @Test
    @DisplayName("Test Player - Check getShape() with null shape")
    public void testPlayer_getShapeWithNullShape() {
        Player player = new Player("Player 1");

        Assertions.assertFalse(player.getShape().isPresent());
    }

    @Test
    @DisplayName("Test Player - Check getShape() with shape")
    public void testPlayer_getShapeWithShape() {
        Player player = new Player("Player 1");
        player.playShape(ShapeEnum.ROCK);

        Assertions.assertEquals(ShapeEnum.ROCK, player.getShape().get());
    }

    @Test
    @DisplayName("Test Player - Check getScore()")
    public void testPlayer_getScore() {
        Player player = new Player("Player 1");

        Assertions.assertEquals(0, player.getScore());
    }

    @Test
    @DisplayName("Test Player - Check incrementScore()")
    public void testPlayer_incrementScore() {
        Player player = new Player("Player 1");
        player.incrementScore();

        Assertions.assertEquals(1, player.getScore());
    }

    @Test
    @DisplayName("Test Player - Check equals() with same object")
    public void testPlayer_equalsWithSameObject() {
        Player player = new Player("Player 1");

        Assertions.assertTrue(player.equals(player));
    }

    @Test
    @DisplayName("Test Player - Check equals() with null")
    public void testPlayer_equalsWithNull() {
        Player player = new Player("Player 1");

        Assertions.assertFalse(player.equals(null));
    }

    @Test
    @DisplayName("Test Player - Check equals() with different class")
    public void testPlayer_equalsWithDifferentClass() {
        Player player = new Player("Player 1");

        Assertions.assertFalse(player.equals("test"));
    }

    @Test
    @DisplayName("Test Player - Check equals() with different name")
    public void testPlayer_equalsWithDifferentName() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        Assertions.assertFalse(player1.equals(player2));
    }

    @Test
    @DisplayName("Test Player - Check equals() with same name")
    public void testPlayer_equalsWithSameName() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 1");

        Assertions.assertFalse(player1.equals(player2));
    }

    @Test
    @DisplayName("Test Player - Check equals() with different shape")
    public void testPlayer_equalsWithDifferentShape() {
        Player player1 = new Player("Player 1");
        player1.playShape(ShapeEnum.ROCK);
        Player player2 = new Player("Player 1");
        player2.playShape(ShapeEnum.SCISSORS);

        Assertions.assertFalse(player1.equals(player2));
    }

    @Test
    @DisplayName("Test Player - Check equals() with same shape")
    public void testPlayer_equalsWithSameShape() {
        Player player1 = new Player("Player 1");
        player1.playShape(ShapeEnum.ROCK);
        Player player2 = new Player("Player 1");
        player2.playShape(ShapeEnum.ROCK);

        Assertions.assertFalse(player1.equals(player2));
    }

    @Test
    @DisplayName("Test Player - Check equals() with different score")
    public void testPlayer_equalsWithDifferentScore() {
        Player player1 = new Player("Player 1");
        player1.incrementScore();
        Player player2 = new Player("Player 1");

        Assertions.assertFalse(player1.equals(player2));
    }

    @Test
    @DisplayName("Test Player - Check equals() with same score")
    public void testPlayer_equalsWithSameScore() {
        Player player1 = new Player("Player 1");
        player1.incrementScore();
        Player player2 = new Player("Player 1");
        player2.incrementScore();

        Assertions.assertFalse(player1.equals(player2));
    }

    @Test
    @DisplayName("Test Player - Check toString()")
    public void testPlayer_toString() {
        Player player = new Player("Player 1");

        Assertions.assertEquals("Player 1", player.toString());
    }

    @Test
    @DisplayName("Test Player - Check hashCode()")
    public void testPlayer_hashCode() {
        Player player = new Player("Player 1");

        Assertions.assertEquals(player.hashCode(), player.hashCode());
    }

    @Test
    @DisplayName("Test Player - Check hashCode() with same name")
    public void testPlayer_hashCodeWithSameName() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 1");

        Assertions.assertNotEquals(player1.hashCode(), player2.hashCode());
    }
}

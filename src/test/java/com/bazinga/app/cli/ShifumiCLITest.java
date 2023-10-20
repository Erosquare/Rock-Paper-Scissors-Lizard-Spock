package com.bazinga.app.cli;

import com.bazinga.game.ShifumiGameEngine;
import com.bazinga.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Unit tests for {@link ShifumiCLI} class
 */
public class ShifumiCLITest {

    @Test
    @DisplayName("Test ShifumiCLI - Check run() with null engine in constructor")
    public void testShifumiCLI_runWithNullEngineInConstructor() {
        ShifumiCLI shifumiCLI = new ShifumiCLI(null, new Player(""), new Player(""));

        Assertions.assertThrows(IllegalStateException.class, shifumiCLI::run);
    }

    @Test
    @DisplayName("Test ShifumiCLI - Check run() with null player1 in constructor")
    public void testShifumiCLI_runWithNullPlayer1InConstructor() {
        ShifumiCLI shifumiCLI = new ShifumiCLI(ShifumiGameEngine.INSTANCE, null, new Player(""));

        Assertions.assertThrows(IllegalStateException.class, shifumiCLI::run);
    }

    @Test
    @DisplayName("Test ShifumiCLI - Check run() with null player2 in constructor")
    public void testShifumiCLI_runWithNullPlayer2InConstructor() {
        ShifumiCLI shifumiCLI = new ShifumiCLI(ShifumiGameEngine.INSTANCE, new Player(""), null);

        Assertions.assertThrows(IllegalStateException.class, shifumiCLI::run);
    }

    @Test
    @DisplayName("Test ShifumiCLI - Check getShapeFromChoice() with null negative choice")
    public void testShifumiCLI_getShapeFromChoiceWithNullChoice() {
        ShifumiCLI shifumiCLI = new ShifumiCLI(ShifumiGameEngine.INSTANCE, new Player(""), new Player(""));

        Assertions.assertFalse(shifumiCLI.getShapeFromChoice(-1).isPresent());
    }

    @ParameterizedTest
    @DisplayName("Test ShifumiCLI - Check getShapeFromChoice() with choice out of range [1;5]")
    @ValueSource(ints = {-1, 0, 6, 7})
    public void testShifumiCLI_getShapeFromChoiceWithChoiceOutOfRange(int choice) {
        ShifumiCLI shifumiCLI = new ShifumiCLI(ShifumiGameEngine.INSTANCE, new Player(""), new Player(""));

        Assertions.assertFalse(shifumiCLI.getShapeFromChoice(choice).isPresent());
    }

    @ParameterizedTest
    @DisplayName("Test ShifumiCLI - Check getShapeFromChoice() with choice between 1 and 5")
    @CsvSource({"1,ROCK", "2,PAPER", "3,SCISSORS", "4,LIZARD", "5,SPOCK"})
    public void testShifumiCLI_getShapeFromChoiceWithChoiceBetween1And5(int choice, String shapeName) {
        ShifumiCLI shifumiCLI = new ShifumiCLI(ShifumiGameEngine.INSTANCE, new Player(""), new Player(""));

        Assertions.assertTrue(shifumiCLI.getShapeFromChoice(choice).isPresent());
        Assertions.assertEquals(shapeName, shifumiCLI.getShapeFromChoice(choice).get().name());
    }
}


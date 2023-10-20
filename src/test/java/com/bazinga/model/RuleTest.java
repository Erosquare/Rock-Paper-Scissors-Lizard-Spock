package com.bazinga.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Rule} class
 */
public class RuleTest {

    @Test
    @DisplayName("Test Rule - Check toString() with verb")
    public void testRule_toStringWithVerb() {
        Rule rule = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);

        Assertions.assertEquals("ROCK crushes SCISSORS", rule.toString());
    }

    @Test
    @DisplayName("Test Rule - Check toString() with constructor with 2 parameters")
    public void testRule_toStringWithConstructor() {
        Rule rule = new Rule(ShapeEnum.ROCK, ShapeEnum.SCISSORS);

        Assertions.assertEquals("ROCK beats SCISSORS", rule.toString());
    }

    @Test
    @DisplayName("Test Rule - Check toString() with null verb")
    public void testRule_toStringWithNullVerb() {
        Rule rule = new Rule(ShapeEnum.ROCK, null, ShapeEnum.SCISSORS);

        Assertions.assertEquals("ROCK beats SCISSORS", rule.toString());
    }

    @Test
    @DisplayName("Test Rule - Check toString() with blank verb")
    public void testRule_toStringWithBlankVerb() {
        Rule rule = new Rule(ShapeEnum.ROCK, "       ", ShapeEnum.SCISSORS);

        Assertions.assertEquals("ROCK beats SCISSORS", rule.toString());
    }

    @Test
    @DisplayName("Test Rule - Check equals() with same object")
    public void testRule_equalsWithSameObject() {
        Rule rule = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);

        Assertions.assertTrue(rule.equals(rule));
    }

    @Test
    @DisplayName("Test Rule - Check equals() with null")
    public void testRule_equalsWithNull() {
        Rule rule = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);

        Assertions.assertFalse(rule.equals(null));
    }

    @Test
    @DisplayName("Test Rule - Check equals() with different class")
    public void testRule_equalsWithDifferentClass() {
        Rule rule = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);

        Assertions.assertFalse(rule.equals("test"));
    }

    @Test
    @DisplayName("Test Rule - Check equals() with different winning shape")
    public void testRule_equalsWithDifferentWinningShape() {
        Rule rule1 = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);
        Rule rule2 = new Rule(ShapeEnum.PAPER, "covers", ShapeEnum.SCISSORS);

        Assertions.assertFalse(rule1.equals(rule2));
    }

    @Test
    @DisplayName("Test Rule - Check equals() with different losing shape")
    public void testRule_equalsWithDifferentLosingShape() {
        Rule rule1 = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);
        Rule rule2 = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.PAPER);

        Assertions.assertFalse(rule1.equals(rule2));
    }

    @Test
    @DisplayName("Test Rule - Check equals() with same shapes")
    public void testRule_equalsWithSameShapes() {
        Rule rule1 = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);
        Rule rule2 = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);

        Assertions.assertTrue(rule1.equals(rule2));
    }

    @Test
    @DisplayName("Test Rule - Check equals() with same shapes but different verbs")
    public void testRule_equalsWithSameShapesButDifferentVerbs() {
        Rule rule1 = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);
        Rule rule2 = new Rule(ShapeEnum.ROCK, "covers", ShapeEnum.SCISSORS);

        Assertions.assertTrue(rule1.equals(rule2));
    }

    @Test
    @DisplayName("Test Rule - Check hashCode() with same shapes")
    public void testRule_hashCodeWithSameShapes() {
        Rule rule1 = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);
        Rule rule2 = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);

        Assertions.assertEquals(rule1.hashCode(), rule2.hashCode());
    }

    @Test
    @DisplayName("Test Rule - Check hashCode() with same shapes but different verbs")
    public void testRule_hashCodeWithSameShapesButDifferentVerbs() {
        Rule rule1 = new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS);
        Rule rule2 = new Rule(ShapeEnum.ROCK, "covers", ShapeEnum.SCISSORS);

        Assertions.assertEquals(rule1.hashCode(), rule2.hashCode());
    }

}

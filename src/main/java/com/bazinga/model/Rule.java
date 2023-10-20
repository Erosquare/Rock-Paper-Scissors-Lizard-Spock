package com.bazinga.model;

import java.util.Objects;

/**
 * A rule of the game. A rule is composed of a winning shape, a verb and a losing shape. The winning shape beats the
 * losing shape.
 */
public class Rule {
    private ShapeEnum winningShape; // The shape that wins
    private String winningVerb; // The verb that describes the winning action
    private ShapeEnum losingShape; // The shape that loses

    /**
     * Create a new rule
     *
     * @param winningShape The shape that wins
     * @param losingShape The shape that loses
     */
    public Rule(ShapeEnum winningShape, ShapeEnum losingShape) {
        this(winningShape, null, losingShape);
    }

    /**
     * Create a new rule
     *
     * @param winningShape The shape that wins
     * @param winningVerb The verb that describes the winning action against the losing shape
     * @param losingShape The shape that loses
     */
    public Rule(ShapeEnum winningShape, String winningVerb, ShapeEnum losingShape) {
        this.winningShape = winningShape;
        this.winningVerb = (winningVerb == null || winningVerb.isBlank())? "beats" : winningVerb;
        this.losingShape = losingShape;
    }

    @Override
    public String toString() {
        return winningShape + " " + winningVerb + " " + losingShape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return winningShape == rule.winningShape && losingShape == rule.losingShape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningShape, losingShape);
    }
}

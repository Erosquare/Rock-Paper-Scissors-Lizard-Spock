package com.bazinga.app;

import com.bazinga.app.cli.ShifumiCLI;
import com.bazinga.game.AbstractShifumiGame;
import com.bazinga.game.ShifumiGameEngine;
import com.bazinga.model.Player;
import com.bazinga.model.Rule;
import com.bazinga.model.ShapeEnum;

import java.util.List;


public class ShifumiApplication {
    static {
        // Add the rules of the game (Rock, Paper, Scissors, Lizard, Spock)
        List<Rule> rulesList = List.of(new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.SCISSORS),
                                       new Rule(ShapeEnum.ROCK, "crushes", ShapeEnum.LIZARD),
                                       new Rule(ShapeEnum.PAPER, "covers", ShapeEnum.ROCK),
                                       new Rule(ShapeEnum.PAPER, "disproves", ShapeEnum.SPOCK),
                                       new Rule(ShapeEnum.SCISSORS, "cuts", ShapeEnum.PAPER),
                                       new Rule(ShapeEnum.SCISSORS, "decapitates", ShapeEnum.LIZARD),
                                       new Rule(ShapeEnum.LIZARD, "poisons", ShapeEnum.SPOCK),
                                       new Rule(ShapeEnum.LIZARD, "eats", ShapeEnum.PAPER),
                                       new Rule(ShapeEnum.SPOCK, "smashes", ShapeEnum.SCISSORS),
                                       new Rule(ShapeEnum.SPOCK, "vaporizes", ShapeEnum.ROCK));

        rulesList.forEach(ShifumiGameEngine.INSTANCE::addRule);
    }

    public static void main(String[] args) {
        AbstractShifumiGame game = new ShifumiCLI(ShifumiGameEngine.INSTANCE, new Player("Player"), new Player("Computer"));
        game.start();
    }
}  
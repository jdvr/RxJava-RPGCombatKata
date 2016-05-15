package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.character.Health;
import es.juandavidvega.rpgcombat.character.MeleeFighter;
import es.juandavidvega.rpgcombat.character.RangedFighter;

public class CharacterTestBuilder {
    
    public static Character newMeleeFighterWith(Health health) {
        return new MeleeFighter(health, 1);
    }

    public static Character newMeleeFighterWith(Health health, Integer level) {
        return new MeleeFighter(health, level);
    }

    public static Character newRangedFighterWith(Health health) {
        return new RangedFighter(health, 1);
    }

    public static Character newRangeFighterWith(Health health, Integer level) {
        return new RangedFighter(health, level);
    }

    public static Health minHealth() {
        return customHealth(Health.MIN_HEALTH_POINTS);
    }

    public static Health maxHealth() {
        return customHealth(Health.MAX_HEALTH_POINTS);
    }

    public static Health customHealth(double customHealLevel) {
        return new Health(customHealLevel);
    }


}

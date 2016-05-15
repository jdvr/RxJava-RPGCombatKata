package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.character.Health;

public class CharacterTestBuilder {
    
    public static Character newCharacterWith(Health health) {
        return new Character(health);
    }

    public static Health minHealth() {
        return customHealth(Health.MIN_HEALTH_POINTS);
    }

    public static Health maxHealth() {
        return customHealth(Health.MAX_HEALTH_POINTS);
    }

    public static Health customHealth(int customHealLevel) {
        return new Health(customHealLevel);
    }


}

package es.juandavidvega.rpgcombat.engine.acctions;

import es.juandavidvega.rpgcombat.character.Character;

public class Attack {
    private final Character attacker;
    private final Integer damagePoints;

    public Attack(Character attacker, Integer damagePoints) {
        this.attacker = attacker;
        this.damagePoints = damagePoints;
    }


    public Character attacker() {
        return attacker;
    }

    public Integer points() {
        return damagePoints;
    }
}

package es.juandavidvega.rpgcombat.engine.acctions;

import es.juandavidvega.rpgcombat.character.Character;

public class HealthAction {

    private final Character healer;
    private final Integer points;

    public HealthAction(Character healer, Integer points) {
        this.healer = healer;
        this.points = points;
    }

    public Character healer() {
        return healer;
    }

    public Integer points() {
        return points;
    }
}

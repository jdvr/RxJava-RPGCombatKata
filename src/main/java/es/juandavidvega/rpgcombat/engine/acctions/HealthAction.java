package es.juandavidvega.rpgcombat.engine.acctions;

import es.juandavidvega.rpgcombat.character.Character;

public class HealthAction {

    private final Character healer;
    private final Double points;

    public HealthAction(Character healer, Double points) {
        this.healer = healer;
        this.points = points;
    }

    public Character healer() {
        return healer;
    }

    public Double points() {
        return points;
    }
}

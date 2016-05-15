package es.juandavidvega.rpgcombat.engine.events;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.Event;

public class IncreaseLifeEvent implements Event {

    private final Double points;

    public IncreaseLifeEvent(Double points) {
        this.points = points;
    }

    public Double points () {
        return points;
    }

    @Override
    public EventTypes type() {
        return EventTypes.IncreaseLife;
    }
}

package es.juandavidvega.rpgcombat.engine.events.character;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.Event;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;

public class IncreaseLifeEvent extends Event {

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

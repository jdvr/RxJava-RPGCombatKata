package es.juandavidvega.rpgcombat.engine.events;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.Event;

public class IncreaseLifeEvent implements Event {

    private final Integer points;

    public IncreaseLifeEvent(Integer points) {
        this.points = points;
    }

    public Integer points () {
        return points;
    }

    @Override
    public EventTypes type() {
        return EventTypes.IncreaseLife;
    }
}

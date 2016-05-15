package es.juandavidvega.rpgcombat.engine.events.character;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.Event;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;

public class IncreaseLifeEvent extends Event {

    private Character target;
    private final Double points;

    public IncreaseLifeEvent(Character target, Double points) {
        this.target = target;
        this.points = points;
    }

    public Double points () {
        return points;
    }

    public Character target(){
        return  target;
    }

    @Override
    public EventTypes type() {
        return EventTypes.IncreaseLife;
    }
}

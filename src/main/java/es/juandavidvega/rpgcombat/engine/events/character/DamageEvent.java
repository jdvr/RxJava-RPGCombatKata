package es.juandavidvega.rpgcombat.engine.events.character;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.Event;
import es.juandavidvega.rpgcombat.engine.events.EventType;

public class DamageEvent implements Event {

    private final Character target;
    private final Double points;

    public DamageEvent(Character target, double points){
        this.target = target;
        this.points = points;
    }

    public Character target(){
        return target;
    }

    public Double points() {
        return points;
    }

    @Override
    public EventType type() {
        return EventType.Damage;
    }
}

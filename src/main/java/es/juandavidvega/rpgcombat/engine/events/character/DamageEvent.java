package es.juandavidvega.rpgcombat.engine.events.character;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.Event;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;

public class DamageEvent extends Event {

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
    public EventTypes type() {
        return EventTypes.Damage;
    }
}

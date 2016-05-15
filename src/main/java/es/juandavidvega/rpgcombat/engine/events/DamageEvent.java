package es.juandavidvega.rpgcombat.engine.events;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.Event;

/**
 * Created by jdvr on 15/05/16.
 */
public class DamageEvent implements Event {

    private final Character target;
    private final Double points;

    public DamageEvent(Character target, double points){
        this.target = target;
        this.points = points;
    }

    public Character targe(){
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

package es.juandavidvega.rpgcombat.engine.events.props;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.Event;
import es.juandavidvega.rpgcombat.engine.events.EventType;
import es.juandavidvega.rpgcombat.props.House;

public class DamagePropsEvent extends Event{

    private final House house;
    private final Integer damage;

    public DamagePropsEvent(House house, Integer damage) {
        this.house = house;
        this.damage = damage;
    }



    @Override
    public EventType type() {
        return EventType.DamageProps;
    }

    @Override
    public Character target() {
        return null;
    }

    public House house() {
        return house;
    }

    public Integer damage () {
        return damage;
    }
}

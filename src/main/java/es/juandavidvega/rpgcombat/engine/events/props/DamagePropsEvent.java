package es.juandavidvega.rpgcombat.engine.events.props;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.Event;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;
import es.juandavidvega.rpgcombat.props.House;

public class DamagePropsEvent extends Event{

    private final House house;
    private final Integer damage;

    public DamagePropsEvent(House house, Integer damage) {
        this.house = house;
        this.damage = damage;
    }



    @Override
    public EventTypes type() {
        return EventTypes.DamageProps;
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

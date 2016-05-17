package es.juandavidvega.rpgcombat.engine.events.props;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.acctions.Attack;
import es.juandavidvega.rpgcombat.engine.events.Event;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;
import es.juandavidvega.rpgcombat.props.House;

public class AttackPropsEvent extends Event{

    private final Attack attack;
    private final House house;

    public AttackPropsEvent(Attack attack, House house) {
        this.attack = attack;
        this.house = house;
    }

    @Override
    public EventTypes type() {
        return EventTypes.AttackProps;
    }

    @Override
    public Character target() {
        return null;
    }
}

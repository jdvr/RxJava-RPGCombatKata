package es.juandavidvega.rpgcombat.engine.events.props;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.acctions.Attack;
import es.juandavidvega.rpgcombat.engine.events.Event;
import es.juandavidvega.rpgcombat.engine.events.EventType;
import es.juandavidvega.rpgcombat.props.House;

public class AttackPropsEvent implements Event{

    private final Attack attack;
    private final House house;

    public AttackPropsEvent(Attack attack, House house) {
        this.attack = attack;
        this.house = house;
    }

    @Override
    public EventType type() {
        return EventType.AttackProps;
    }

    @Override
    public Character target() {
        return null;
    }

    public Character attacker() {
        return attack.attacker();
    }

    public House house() {
        return house;
    }

    public Integer damage() {
        return attack.points();
    }
}

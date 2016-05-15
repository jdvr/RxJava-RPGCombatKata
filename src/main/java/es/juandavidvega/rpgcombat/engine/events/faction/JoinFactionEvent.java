package es.juandavidvega.rpgcombat.engine.events.faction;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.Event;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;
import es.juandavidvega.rpgcombat.faction.Faction;

/**
 * Created by jdvr on 15/05/16.
 */
public class JoinFactionEvent extends Event{

    private final Faction faction;
    private final Character target;

    public JoinFactionEvent(Faction faction, Character target) {
        this.faction = faction;
        this.target = target;
    }

    public Faction faction() {
        return faction;
    }

    @Override
    public EventTypes type() {
        return EventTypes.JoinFaction;
    }

    @Override
    public Character target() {
        return target;
    }
}

package es.juandavidvega.rpgcombat.engine.events.faction;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.Event;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;
import es.juandavidvega.rpgcombat.faction.Faction;

public class FactionEvent extends Event{

    private final Faction faction;
    private final Character target;
    private EventTypes type;

    public FactionEvent(Faction faction, Character target, EventTypes type) {
        this.faction = faction;
        this.target = target;
        this.type = type;
    }

    public Faction faction() {
        return faction;
    }

    @Override
    public EventTypes type() {
        return type;
    }

    @Override
    public Character target() {
        return target;
    }
}

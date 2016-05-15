package es.juandavidvega.rpgcombat.engine.events.game;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.Event;
import es.juandavidvega.rpgcombat.engine.acctions.HealthAction;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;

/**
 * Created by jdvr on 15/05/16.
 */
public class HealthEvent extends Event {
    private final HealthAction health;
    private final Character target;

    public HealthEvent(HealthAction health, Character target) {
        this.health = health;
        this.target = target;
    }

    public Character target() {
        return target;
    }

    public boolean isSameCharacter() {
        return health.healer() == target;
    }

    @Override
    public EventTypes type() {
        return EventTypes.Health;
    }

    public Double points() {
        return health.points();
    }
}

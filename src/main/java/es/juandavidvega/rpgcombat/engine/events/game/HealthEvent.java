package es.juandavidvega.rpgcombat.engine.events.game;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.Event;
import es.juandavidvega.rpgcombat.engine.acctions.HealthAction;
import es.juandavidvega.rpgcombat.engine.events.EventType;

/**
 * Created by jdvr on 15/05/16.
 */
public class HealthEvent implements Event {
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
    public EventType type() {
        return EventType.Health;
    }

    public Double points() {
        return health.points();
    }


    public Boolean areAllies() {
        return health.healer().isAllieOf(target);
    }
}

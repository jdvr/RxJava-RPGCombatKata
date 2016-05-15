package es.juandavidvega.rpgcombat.engine.events;

import es.juandavidvega.rpgcombat.engine.GameEvent;

public class GameEventChecker {

    public boolean isAttack(Object object){
        return isEventInstance(object) && is((GameEvent) object, GameEvents.Attack);
    }

    public boolean isDamage(Object object) {
        return isEventInstance(object) && is((GameEvent) object, GameEvents.Damage);
    }

    private boolean isEventInstance(Object object) {
        return object instanceof GameEvent;
    }

    private boolean is(GameEvent gameEvent, GameEvents type) {
        return gameEvent.type() == type;
    }
}

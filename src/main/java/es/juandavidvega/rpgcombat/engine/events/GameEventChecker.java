package es.juandavidvega.rpgcombat.engine.events;

import es.juandavidvega.rpgcombat.engine.Event;

public class GameEventChecker {

    public boolean isAttack(Object object){
        return isEventInstance(object) && is((Event) object, EventTypes.Attack);
    }

    public boolean isDamage(Object object) {
        return isEventInstance(object) && is((Event) object, EventTypes.Damage);
    }

    public boolean isHealth(Object object) {
        return isEventInstance(object) && is((Event) object, EventTypes.Health);
    }

    public boolean isLifeIncrease(Object object) {
        return isEventInstance(object) && is((Event) object, EventTypes.IncreaseLife);
    }

    private boolean isEventInstance(Object object) {
        return object instanceof Event;
    }

    private boolean is(Event event, EventTypes type) {
        return event.type() == type;
    }
}

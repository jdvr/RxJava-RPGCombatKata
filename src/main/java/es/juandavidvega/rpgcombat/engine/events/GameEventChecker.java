package es.juandavidvega.rpgcombat.engine.events;

public class GameEventChecker {

    public boolean isAttack(Object object){
        return isEventInstance(object) && is((Event) object, EventType.Attack);
    }

    public boolean isDamage(Object object) {
        return isEventInstance(object) && is((Event) object, EventType.Damage);
    }

    public boolean isHealth(Object object) {
        return isEventInstance(object) && is((Event) object, EventType.Health);
    }

    public boolean isLifeIncrease(Object object) {
        return isEventInstance(object) && is((Event) object, EventType.IncreaseLife);
    }

    public boolean isFactionJoin(Object object) {
        return isEventInstance(object) && is((Event) object, EventType.JoinFaction);
    }

    public boolean isLeaveFaction(Object object) {
        return isEventInstance(object) && is((Event) object, EventType.LeaveFaction);
    }

    public Boolean isPropAttack(Object object) {
        return isEventInstance(object) && is((Event) object, EventType.AttackProps);
    }

    public Boolean isPropDamage(Object object) {
        return isEventInstance(object) && is((Event) object, EventType.DamageProps);
    }

    private boolean isEventInstance(Object object) {
        return object instanceof Event;
    }

    private boolean is(Event event, EventType type) {
        return event.type() == type;
    }
}

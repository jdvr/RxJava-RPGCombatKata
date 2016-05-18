package es.juandavidvega.rpgcombat.engine.events;

import es.juandavidvega.rpgcombat.character.Character;

public abstract class Event {
    public abstract Targetable target();
    public abstract EventTypes type();
    public void publishOn(){
        EventBus.get().send(this);
    }
}

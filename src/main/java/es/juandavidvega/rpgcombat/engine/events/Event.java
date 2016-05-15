package es.juandavidvega.rpgcombat.engine.events;

import es.juandavidvega.rpgcombat.character.Character;

public abstract class Event {
    public abstract EventTypes type();
    public abstract Character target();
    public void publishOn(EventBus bus){
        bus.send(this);
    }
}

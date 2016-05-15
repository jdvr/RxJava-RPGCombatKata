package es.juandavidvega.rpgcombat.engine;

import es.juandavidvega.rpgcombat.engine.events.EventTypes;

public abstract class Event {
    public abstract EventTypes type();

    public void publishOn(EventBus bus){
        bus.send(this);
    }
}

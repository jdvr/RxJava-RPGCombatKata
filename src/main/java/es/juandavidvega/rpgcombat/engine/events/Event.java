package es.juandavidvega.rpgcombat.engine.events;

public interface Event {
    Targetable target();
    EventType type();

    default void publish(){
        EventBus.get().send(this);
    }

    default boolean is(EventType type) {
        return type.equals(type());
    }
}

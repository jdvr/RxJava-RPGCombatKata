package es.juandavidvega.rpgcombat.engine.subscription;

import es.juandavidvega.rpgcombat.engine.events.EventType;
import rx.Subscription;

import java.util.HashMap;
import java.util.Map;

public class Subscriptions {
    private final Map<EventType, Subscription> subscriptions = new HashMap<>();

    public void add(EventType event, Subscription subscription){
        subscriptions.put(event, subscription);
    }

    public void unsubscribe(EventType event){
        Subscription subscription = subscriptions.get(event);
        if (subscription == null) return;
        subscription.unsubscribe();
        subscriptions.remove(event);
    }

    public void unsubscribeAll() {
        subscriptions
                .values()
                .forEach(subscription -> subscription.unsubscribe());
        subscriptions.clear();
    }
}

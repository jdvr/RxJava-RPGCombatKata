package es.juandavidvega.rpgcombat.engine.subscription;

import es.juandavidvega.rpgcombat.engine.events.EventTypes;
import rx.Subscription;

import java.util.HashMap;
import java.util.Map;

public class Subscriptions {
    private final Map<EventTypes, Subscription> subscriptions = new HashMap<>();

    public void add(EventTypes event, Subscription subscription){
        subscriptions.put(event, subscription);
    }

    public void unsubscribe(EventTypes event){
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

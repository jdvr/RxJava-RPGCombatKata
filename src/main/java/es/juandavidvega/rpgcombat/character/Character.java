package es.juandavidvega.rpgcombat.character;

import es.juandavidvega.rpgcombat.engine.EventBus;
import es.juandavidvega.rpgcombat.engine.events.DamageEvent;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;
import es.juandavidvega.rpgcombat.engine.events.GameEventChecker;
import es.juandavidvega.rpgcombat.engine.events.IncreaseLifeEvent;
import es.juandavidvega.rpgcombat.engine.subscription.Subscriptions;
import rx.Subscription;

public class Character {

    private Health health;
    private final Subscriptions subscriptions = new Subscriptions();

    public Character(Health health) {
        this.health = health;
        listenDamages();
        listenHealth();
    }

    private void listenHealth() {
        Subscription subscription = EventBus.get().toObserverable()
                .filter(event -> new GameEventChecker().isLifeIncrease(event))
                .map(gameEvent -> (IncreaseLifeEvent) gameEvent)
                .subscribe(this::manageHealth);
        subscriptions.add(EventTypes.IncreaseLife, subscription);
    }

    private void listenDamages() {
        Subscription subscribe = EventBus.get().toObserverable()
                .filter(event -> new GameEventChecker().isDamage(event))
                .map(gameEvent -> (DamageEvent) gameEvent)
                .subscribe(this::manageDamage);
        subscriptions.add(EventTypes.Damage, subscribe);
    }

    private void manageDamage(DamageEvent damageEvent) {
        this.receive(damageEvent.points());
    }

    private void manageHealth(IncreaseLifeEvent event) {
        this.health(event.points());
    }

    public void receive(Integer damage) {
        health.subtract(damage);
        if(AmIDead()) subscriptions.unsubscribeAll();
    }

    public boolean isAlive() {
        return health.isAlive();
    }

    public Health health() {
        return health;
    }

    public void health(Integer health) {
        this.health.add(health);
    }

    private boolean AmIDead() {
        return !isAlive();
    }
}

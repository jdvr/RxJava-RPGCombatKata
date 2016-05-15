package es.juandavidvega.rpgcombat.character;

import es.juandavidvega.rpgcombat.engine.events.Event;
import es.juandavidvega.rpgcombat.engine.events.EventBus;
import es.juandavidvega.rpgcombat.engine.events.character.DamageEvent;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;
import es.juandavidvega.rpgcombat.engine.events.GameEventChecker;
import es.juandavidvega.rpgcombat.engine.events.character.IncreaseLifeEvent;
import es.juandavidvega.rpgcombat.engine.subscription.Subscriptions;
import es.juandavidvega.rpgcombat.faction.Faction;
import rx.Subscription;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {

    private Health health;
    private Integer level;
    private final Subscriptions subscriptions = new Subscriptions();
    private List<Faction> factions = new ArrayList<>();

    public Character(Health health, Integer level) {
        this.health = health;
        this.level = level;
        listenDamages();
        listenHealth();
    }

    public void receive(Double damage) {
        health.subtract(damage);
        if(AmIDead()) subscriptions.unsubscribeAll();
    }

    public boolean isAlive() {
        return health.isAlive();
    }

    public Health health() {
        return health;
    }

    public Integer level() {
        return level;
    }

    public void health(Double health) {
        this.health.add(health);
    }

    public abstract Integer range();

    private EventBus getEventBus() {
        return EventBus.get();
    }

    private boolean AmIDead() {
        return !isAlive();
    }

    private void listenHealth() {
        Subscription subscription = getEventBus().toObservable()
                .filter(event -> new GameEventChecker().isLifeIncrease(event))
                .map(gameEvent -> (IncreaseLifeEvent) gameEvent)
                .filter(this::isMe)
                .subscribe(this::manageHealth);
        subscriptions.add(EventTypes.IncreaseLife, subscription);
    }

    private void listenDamages() {
        Subscription subscribe = getEventBus().toObservable()
                .filter(event -> new GameEventChecker().isDamage(event))
                .map(gameEvent -> (DamageEvent) gameEvent)
                .filter(this::isMe)
                .subscribe(this::manageDamage);
        subscriptions.add(EventTypes.Damage, subscribe);
    }

    private Boolean isMe(Event event) {
        return event.target() == this;
    }

    private void manageDamage(DamageEvent damageEvent) {
        this.receive(damageEvent.points());
    }

    private void manageHealth(IncreaseLifeEvent event) {
        this.health(event.points());
    }

    public List<Faction> factions(){
        return factions;
    }
}

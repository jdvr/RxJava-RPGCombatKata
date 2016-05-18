package es.juandavidvega.rpgcombat.character;

import es.juandavidvega.rpgcombat.engine.events.*;
import es.juandavidvega.rpgcombat.engine.events.character.DamageEvent;
import es.juandavidvega.rpgcombat.engine.events.character.IncreaseLifeEvent;
import es.juandavidvega.rpgcombat.engine.events.faction.FactionEvent;
import es.juandavidvega.rpgcombat.engine.subscription.Subscriptions;
import es.juandavidvega.rpgcombat.faction.Faction;
import rx.Subscription;

import java.util.*;

import static es.juandavidvega.rpgcombat.engine.events.EventType.*;

public abstract class Character implements Targetable {

    private Health health;
    private Integer level;
    private final Subscriptions subscriptions = new Subscriptions();
    private Set<Faction> factions = new HashSet<>();

    public Character(Health health, Integer level) {
        this.health = health;
        this.level = level;
        listenDamages();
        listenHealth();
        listenJoinFactions();
        listenLeaveFactions();
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

    public Set<Faction> factions(){
        return factions;
    }

    public void addFaction(Faction faction) {
        this.factions.add(faction);
    }

    private void joinFaction(FactionEvent event) {
        addFaction(event.faction());
    }

    private void leaveFaction(FactionEvent event) {
        factions.remove(event.faction());
    }

    private EventBus getEventBus() {
        return EventBus.get();
    }

    private boolean AmIDead() {
        return !isAlive();
    }

    private void listenHealth() {
        Subscription subscription = getEventBus().streamOf(IncreaseLife, IncreaseLifeEvent.class)
                .filter(this::isMe)
                .subscribe(this::manageHealth);
        subscriptions.add(IncreaseLife, subscription);
    }

    private void listenDamages() {
        Subscription subscribe = getEventBus().streamOf(Damage, DamageEvent.class)
                .filter(this::isMe)
                .subscribe(this::manageDamage);
        subscriptions.add(Damage, subscribe);
    }

    private void listenJoinFactions() {
        Subscription subscribe = getEventBus().streamOf(JoinFaction, FactionEvent.class)
                .filter(this::isMe)
                .subscribe(this::joinFaction);
        subscriptions.add(JoinFaction, subscribe);
    }

    private void listenLeaveFactions() {
        Subscription subscribe = getEventBus().streamOf(LeaveFaction, FactionEvent.class)
                .filter(this::isMe)
                .subscribe(this::leaveFaction);
        subscriptions.add(LeaveFaction, subscribe);
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

    public Boolean isAllieOf(Character target) {
        long count = target.factions()
                .stream()
                .filter(factions::contains)
                .count();
        return count != 0;
    }
}

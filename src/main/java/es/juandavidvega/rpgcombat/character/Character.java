package es.juandavidvega.rpgcombat.character;

import es.juandavidvega.rpgcombat.engine.events.*;
import es.juandavidvega.rpgcombat.engine.events.character.DamageEvent;
import es.juandavidvega.rpgcombat.engine.events.character.IncreaseLifeEvent;
import es.juandavidvega.rpgcombat.engine.events.faction.FactionEvent;
import es.juandavidvega.rpgcombat.engine.subscription.Subscriptions;
import es.juandavidvega.rpgcombat.faction.Faction;
import rx.Observable;

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
        startListen();
    }

    private void startListen() {
        damageEvent();
        healthEvent();
        joinFactionsEvent();
        leaveFactionsEvent();
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

    private void healthEvent() {
        subscriptions.add(IncreaseLife,
                this.<IncreaseLifeEvent>getObservable(IncreaseLife)
                .subscribe(this::manageHealth));
    }

    private void damageEvent() {
        subscriptions.add(Damage,
                this.<DamageEvent>getObservable(Damage)
                .subscribe(this::manageDamage));
    }

    private void joinFactionsEvent() {
        subscriptions.add(JoinFaction,
                this.<FactionEvent>getObservable(JoinFaction)
                .subscribe(this::joinFaction));
    }

    private void leaveFactionsEvent() {
        subscriptions.add(LeaveFaction,
                this.<FactionEvent>getObservable(LeaveFaction)
                .subscribe(this::leaveFaction));
    }

    private <T extends Event> Observable<T> getObservable(EventType type) {
        return getEventBus().<T>streamOf(type).filter(this::isMe);
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
        return target.factions()
                .stream()
                .anyMatch(factions::contains);
    }
}

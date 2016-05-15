package es.juandavidvega.rpgcombat.character;

import es.juandavidvega.rpgcombat.engine.EventBus;
import es.juandavidvega.rpgcombat.engine.events.DamageEvent;
import es.juandavidvega.rpgcombat.engine.events.GameEventChecker;
import es.juandavidvega.rpgcombat.engine.events.IncreaseLifeEvent;

public class Character {

    private Health health;

    public Character(Health health) {
        this.health = health;
        listenDamages();
        listenHealth();
    }

    private void listenHealth() {
        EventBus.get().toObserverable()
                .filter(event -> new GameEventChecker().isLifeIncrease(event))
                .map(gameEvent -> (IncreaseLifeEvent) gameEvent)
                .subscribe(this::manageHealth);
    }

    private void listenDamages() {
        EventBus.get().toObserverable()
                .filter(event -> new GameEventChecker().isDamage(event))
                .map(gameEvent -> (DamageEvent) gameEvent)
                .subscribe(this::manageDamage);
    }

    private void manageDamage(DamageEvent damageEvent) {
        this.receive(damageEvent.points());
    }

    private void manageHealth(IncreaseLifeEvent event) {
        this.health(event.points());
    }

    public void receive(Integer damage) {
        health.subtract(damage);
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
}

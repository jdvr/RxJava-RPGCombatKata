package es.juandavidvega.rpgcombat.character;

import es.juandavidvega.rpgcombat.engine.EventBus;
import es.juandavidvega.rpgcombat.engine.Event;
import es.juandavidvega.rpgcombat.engine.events.DamageEvent;
import es.juandavidvega.rpgcombat.engine.events.GameEventChecker;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;

public class Character {

    private Health health;

    public Character(Health health) {
        this.health = health;
        listenDamages();
    }

    private void listenDamages() {
        EventBus.get().toObserverable()
                .filter(event -> new GameEventChecker().isDamage(event))
                .map(gameEvent -> (DamageEvent) gameEvent)
                .filter(this::isMe)
                .subscribe(this::manageDamage);
    }

    private void manageDamage(DamageEvent damageEvent) {
        this.receive(damageEvent.points());
    }

    private Boolean isMe(DamageEvent damageEvent) {
        return damageEvent.targe() == this;
    }

    private Boolean isDamageEvent(Event event) {
        return event.type() == EventTypes.Damage;
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

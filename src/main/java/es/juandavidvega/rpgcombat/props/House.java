package es.juandavidvega.rpgcombat.props;


import es.juandavidvega.rpgcombat.character.Health;
import es.juandavidvega.rpgcombat.engine.events.EventBus;
import es.juandavidvega.rpgcombat.engine.events.GameEventChecker;
import es.juandavidvega.rpgcombat.engine.events.props.DamagePropsEvent;

public class House {

    private final Health health;

    public House(Health health) {
        this.health = health;
        listenDamages();
    }


    private void listenDamages() {
        getBus().toObservable()
                .filter(event -> new GameEventChecker().isPropDamage(event))
                .map(gameEvent -> (DamagePropsEvent) gameEvent)
                .filter(this::IsThisTheTarget)
                .subscribe(this::receive);
    }

    private Boolean IsThisTheTarget(DamagePropsEvent damagePropsEvent) {
        return damagePropsEvent.house() == this;
    }


    public Health health() {
        return health;
    }

    private EventBus getBus() {
        return EventBus.get();
    }

    public void receive(DamagePropsEvent damagePropsEvent) {
        health.subtract(new Double(damagePropsEvent.damage()));
    }
}

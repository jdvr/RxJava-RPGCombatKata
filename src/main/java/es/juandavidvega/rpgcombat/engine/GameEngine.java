package es.juandavidvega.rpgcombat.engine;

import es.juandavidvega.rpgcombat.engine.events.*;

public class GameEngine {

    private EventBus bus;
    private final GameEventChecker eventChecker = new GameEventChecker();
    public GameEngine(EventBus bus){
        this.bus = bus;
        subscribeAttacks(bus);
        subscribeHealth(bus);
    }

    private void subscribeHealth(EventBus bus) {
        bus.toObserverable()
                .filter(eventChecker::isHealth)
                .map(gameEvent -> (HealthEvent) gameEvent)
                .filter(this::isSameCharacter)
                .subscribe(this::sendHealth);
    }

    private void subscribeAttacks(EventBus bus) {
        bus.toObserverable()
                .filter(event -> new GameEventChecker().isAttack(event))
                .map(gameEvent -> (AttackEvent) gameEvent)
                .filter(this::isNotSameCharacter)
                .subscribe(this::sendDamage);
    }

    private void sendDamage(AttackEvent event) {
        double damage = new DamageEventPointsCalculator(event).calculate();
        bus.send(new DamageEvent(event.target(), damage));
    }

    private void sendHealth(HealthEvent event) {
        bus.send(new IncreaseLifeEvent(event.points()));
    }

    private boolean isSameCharacter(HealthEvent healthEvent) {
        return healthEvent.isSameCharacter();
    }

    private Boolean isNotSameCharacter(AttackEvent event) {
        return event.areDifferentCharacters();
    }

    public void perform(Event event) {
        bus.send(event);
    }
}

package es.juandavidvega.rpgcombat.engine;

import es.juandavidvega.rpgcombat.engine.events.*;
import es.juandavidvega.rpgcombat.engine.events.character.DamageEvent;
import es.juandavidvega.rpgcombat.engine.events.character.DamageEventPointsCalculator;
import es.juandavidvega.rpgcombat.engine.events.character.IncreaseLifeEvent;
import es.juandavidvega.rpgcombat.engine.events.game.AttackEvent;
import es.juandavidvega.rpgcombat.engine.events.game.HealthEvent;
import es.juandavidvega.rpgcombat.map.RangeCalculator;

public class GameEngine {

    private EventBus bus;
    private final GameEventChecker eventChecker = new GameEventChecker();
    public GameEngine(EventBus bus, RangeCalculator rangeCalculator){
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
        new DamageEvent(event.target(), damage).publishOn(bus);
    }

    private void sendHealth(HealthEvent event) {
        new IncreaseLifeEvent(event.target(), event.points()).publishOn(bus);
    }

    private boolean isSameCharacter(HealthEvent healthEvent) {
        return healthEvent.isSameCharacter();
    }

    private Boolean isNotSameCharacter(AttackEvent event) {
        return event.areDifferentCharacters();
    }

}

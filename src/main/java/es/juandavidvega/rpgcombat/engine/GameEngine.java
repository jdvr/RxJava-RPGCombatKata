package es.juandavidvega.rpgcombat.engine;

import es.juandavidvega.rpgcombat.engine.events.*;
import es.juandavidvega.rpgcombat.engine.events.character.DamageEvent;
import es.juandavidvega.rpgcombat.engine.events.character.DamageEventPointsCalculator;
import es.juandavidvega.rpgcombat.engine.events.character.IncreaseLifeEvent;
import es.juandavidvega.rpgcombat.engine.events.game.AttackEvent;
import es.juandavidvega.rpgcombat.engine.events.game.HealthEvent;
import es.juandavidvega.rpgcombat.engine.events.props.AttackPropsEvent;
import es.juandavidvega.rpgcombat.engine.events.props.DamagePropsEvent;
import es.juandavidvega.rpgcombat.map.RangeCalculator;

import static es.juandavidvega.rpgcombat.engine.events.EventType.*;

public class GameEngine {

    private EventBus bus;
    private RangeCalculator rangeCalculator;
    private final GameEventChecker eventChecker = new GameEventChecker();

    public GameEngine(EventBus bus, RangeCalculator rangeCalculator){
        this.bus = bus;
        this.rangeCalculator = rangeCalculator;
        subscribeAttacks(bus);
        subscribeHealth(bus);
        subscribePropsAttacks(bus);
    }

    private void subscribeHealth(EventBus bus) {
        bus.streamOf(Health, HealthEvent.class)
                .filter(this::isSameCharacterOrAllies)
                .subscribe(this::sendHealth);
    }

    private void subscribeAttacks(EventBus bus) {
        bus.streamOf(Attack, AttackEvent.class)
                .filter(this::isNotSameCharacter)
                .filter(this::isInRange)
                .filter(this::areNotAllies)
                .subscribe(this::sendDamage);
    }

    private void subscribePropsAttacks(EventBus bus) {
        bus.streamOf(AttackProps, AttackPropsEvent.class)
                .filter(this::isInRange)
                .subscribe(this::sendPropDamage);
    }

    private Boolean areNotAllies(AttackEvent attackEvent) {
        return !attackEvent.areAllies();
    }

    private Boolean isInRange(AttackEvent attackEvent) {
        return rangeCalculator.rangeBetween(attackEvent.attacker(), attackEvent.target()) <= attackEvent.attacker().range();
    }

    private Boolean isInRange(AttackPropsEvent attackEvent) {
        return rangeCalculator.rangeBetween(attackEvent.attacker(), attackEvent.house()) <= attackEvent.attacker().range();
    }


    private void sendPropDamage (AttackPropsEvent attackPropsEvent) {
        new DamagePropsEvent(attackPropsEvent.house(), attackPropsEvent.damage()).publishOn();
    }

    private void sendDamage(AttackEvent event) {
        double damage = new DamageEventPointsCalculator(event).calculate();
        new DamageEvent(event.target(), damage).publishOn();
    }

    private void sendHealth(HealthEvent event) {
        new IncreaseLifeEvent(event.target(), event.points()).publishOn();
    }

    private boolean isSameCharacterOrAllies(HealthEvent healthEvent) {
        return healthEvent.isSameCharacter() || healthEvent.areAllies();
    }

    private Boolean isNotSameCharacter(AttackEvent event) {
        return event.areDifferentCharacters();
    }

}

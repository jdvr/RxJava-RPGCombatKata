package es.juandavidvega.rpgcombat.engine;

import es.juandavidvega.rpgcombat.engine.events.AttackEvent;
import es.juandavidvega.rpgcombat.engine.events.DamageEvent;
import es.juandavidvega.rpgcombat.engine.events.GameEventChecker;

public class GameEngine {

    private EventBus bus;

    public GameEngine(EventBus bus){
        this.bus = bus;
        subscribeAttacks(bus);
    }

    private void subscribeAttacks(EventBus bus) {
        bus.toObserverable()
                .filter(event -> new GameEventChecker().isAttack(event))
                .map(gameEvent -> (AttackEvent) gameEvent)
                .filter(this::isNotSameCharacter)
                .subscribe(this::sendDamage);
    }

    private void sendDamage(AttackEvent event) {
        bus.send(new DamageEvent(event.target(), event.points()));
    }

    private Boolean isNotSameCharacter(AttackEvent event) {
        return event.areDifferentCharacters();
    }

    public void perform(Event event) {
        bus.send(event);
    }
}

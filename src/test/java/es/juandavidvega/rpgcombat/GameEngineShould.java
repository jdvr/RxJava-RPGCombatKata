package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.acctions.Attack;
import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.EventBus;
import es.juandavidvega.rpgcombat.engine.GameEngine;
import es.juandavidvega.rpgcombat.engine.events.AttackEvent;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameEngineShould {

    EventBus bus = EventBus.get();

    @Test
    public void
    send_attack_between_characters (){
        Character attacker = anyCharacter();
        Character target = anyCharacter();
        GameEngine engine = new GameEngine(bus);
        Attack attack = new Attack(attacker, 100);
        engine.perform(new AttackEvent(attack, target));
        assertThat(target.health().points()).isEqualTo(900);
    }

    private Character anyCharacter() {
        return CharacterTestBuilder.newCharacterWith(CharacterTestBuilder.maxHealth());
    }

}

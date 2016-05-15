package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.engine.acctions.Attack;
import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.EventBus;
import es.juandavidvega.rpgcombat.engine.GameEngine;
import es.juandavidvega.rpgcombat.engine.events.AttackEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameEngineShould {

    private EventBus bus;
    private Character attacker;
    private Character target;
    private GameEngine engine;

    @Before
    public void setUp (){
        bus = EventBus.get();
        attacker = anyCharacter();
        target = anyCharacter();
        engine = new GameEngine(bus);
    }

    @Test
    public void
    send_attack_between_characters (){
        Attack attack = new Attack(attacker, 100);
        engine.perform(new AttackEvent(attack, target));
        assertThat(target.health().points()).isEqualTo(900);
    }

    @Test
    public void
    avoid_to_character_damage_to_himself() {
        Integer pointsBeforeAttack = attacker.health().points();
        Attack attack = new Attack(attacker, 100);
        engine.perform(new AttackEvent(attack, attacker));
        assertThat(attacker.health().points()).isEqualTo(pointsBeforeAttack);
    }

    @After
    public void clearBus(){
        EventBus.destroy();
    }


    private Character anyCharacter() {
        return CharacterTestBuilder.newCharacterWith(CharacterTestBuilder.maxHealth());
    }

}

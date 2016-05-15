package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.engine.acctions.Attack;
import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.EventBus;
import es.juandavidvega.rpgcombat.engine.GameEngine;
import es.juandavidvega.rpgcombat.engine.acctions.HealthAction;
import es.juandavidvega.rpgcombat.engine.events.AttackEvent;
import es.juandavidvega.rpgcombat.engine.events.HealthEvent;
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

    @Test
    public void
    send_health_to_character_when_he_heath_himself() {
        Character lowHealthCharacter = CharacterTestBuilder.newCharacterWith(CharacterTestBuilder.customHealth(700));
        Integer pointsBeforeAttack = lowHealthCharacter.health().points();
        Integer addedPoints = 50;
        HealthAction health = new HealthAction(lowHealthCharacter, addedPoints);
        engine.perform(new HealthEvent(health, lowHealthCharacter));
        assertThat(lowHealthCharacter.health().points()).isEqualTo(pointsBeforeAttack + addedPoints);
    }

    @After
    public void clearBus(){
        EventBus.destroy();
    }


    private Character anyCharacter() {
        return CharacterTestBuilder.newCharacterWith(CharacterTestBuilder.maxHealth());
    }

}

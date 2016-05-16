package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.engine.acctions.Attack;
import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.EventBus;
import es.juandavidvega.rpgcombat.engine.GameEngine;
import es.juandavidvega.rpgcombat.engine.acctions.HealthAction;
import es.juandavidvega.rpgcombat.engine.events.game.AttackEvent;
import es.juandavidvega.rpgcombat.engine.events.game.HealthEvent;
import es.juandavidvega.rpgcombat.faction.Faction;
import es.juandavidvega.rpgcombat.map.RangeCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameEngineShould {

    public static final int MINIMUM_RANGE = 1;
    private EventBus bus;
    private Character attacker;
    private Character target;
    private GameEngine engine;
    private RangeCalculator rangeCalculator = mock(RangeCalculator.class);



    @Before
    public void setUp (){
        bus = EventBus.get();
        attacker = anyCharacter();
        target = anyCharacter();
        engine = new GameEngine(bus, rangeCalculator);
        when(rangeCalculator.rangeBetween(attacker, target)).thenReturn(MINIMUM_RANGE);
    }

    @Test
    public void
    send_attack_between_characters (){
        Attack attack = new Attack(attacker, 100);
        new AttackEvent(attack, target).publishOn(bus);
        assertThat(target.health().points()).isEqualTo(900);
        assertThat(attacker.health().points()).isEqualTo(1000);
    }

    @Test
    public void
    avoid_to_character_damage_to_himself() {
        double pointsBeforeAttack = attacker.health().points();
        Attack attack = new Attack(attacker, 100);
        new AttackEvent(attack, attacker).publishOn(bus);
        assertThat(attacker.health().points()).isEqualTo(pointsBeforeAttack);
    }

    @Test
    public void
    send_health_to_character_when_he_heath_himself() {
        Character lowHealthCharacter = CharacterTestBuilder.newMeleeFighterWith(CharacterTestBuilder.customHealth(700));
        double pointsBeforeAttack = lowHealthCharacter.health().points();
        double addedPoints = 50;
        HealthAction health = new HealthAction(lowHealthCharacter, addedPoints);
        new HealthEvent(health, lowHealthCharacter).publishOn(bus);
        assertThat(lowHealthCharacter.health().points()).isEqualTo(pointsBeforeAttack + addedPoints);
    }

    @Test
    public void
    send_attack_with_reduced_damage_when_attacker_is_at_least_five_level_above_target() {
        attacker = CharacterTestBuilder.newMeleeFighterWith(CharacterTestBuilder.maxHealth(), 40);
        target = CharacterTestBuilder.newMeleeFighterWith(CharacterTestBuilder.maxHealth(), 50);
        Attack attack = new Attack(attacker, 100);
        new AttackEvent(attack, target).publishOn(bus);
        assertThat(target.health().points()).isEqualTo(950);
    }

    @Test
    public void
    send_attack_with_boosted_damage_when_attacker_is_at_least_five_level_below_target() {
        attacker = CharacterTestBuilder.newMeleeFighterWith(CharacterTestBuilder.maxHealth(), 50);
        target = CharacterTestBuilder.newMeleeFighterWith(CharacterTestBuilder.maxHealth(), 40);
        Attack attack = new Attack(attacker, 100);
        new AttackEvent(attack, target).publishOn(bus);
        assertThat(target.health().points()).isEqualTo(850);
    }


    @Test
    public void
    send_attack_only_when_enemy_is_in_attacker_range() {
        Character otherAttacker = anyCharacter();
        Character otherEnemy = anyCharacter();
        when(rangeCalculator.rangeBetween(otherAttacker, otherEnemy)).thenReturn(5);
        Attack attack = new Attack(otherAttacker, 100);
        new AttackEvent(attack, otherEnemy).publishOn(bus);
        assertThat(otherEnemy.health().points()).isEqualTo(otherAttacker.health().points());
    }

    @Test
    public void
    send_large_range_when_attacker_is_ranged_fighter() {
        Character otherAttacker = CharacterTestBuilder.newRangedFighterWith(CharacterTestBuilder.maxHealth());
        Character otherEnemy = anyCharacter();
        when(rangeCalculator.rangeBetween(otherAttacker, otherEnemy)).thenReturn(15);
        Attack attack = new Attack(otherAttacker, 100);
        new AttackEvent(attack, otherEnemy).publishOn(bus);
        assertThat(otherEnemy.health().points()).isEqualTo(900);
    }

    @Test
    public void
    avoid_character_to_send_damage_to_his_allies() {
        Faction faction = new Faction("faction");
        attacker.addFaction(faction);
        target.addFaction(faction);
        Attack attack = new Attack(attacker, 100);
        new AttackEvent(attack, target).publishOn(bus);
        assertThat(attacker.health().points()).isEqualTo(target.health().points());
    }


    @Test
    public void
    send_health_to_character_when_he_heath_an_allie() {
        Faction faction = new Faction("faction");
        Character healer = CharacterTestBuilder.newMeleeFighterWith(CharacterTestBuilder.maxHealth());
        Character allie = CharacterTestBuilder.newMeleeFighterWith(CharacterTestBuilder.customHealth(500d));
        healer.addFaction(faction);
        allie.addFaction(faction);
        double pointsBeforeHealth = allie.health().points();
        double addedPoints = 50;
        HealthAction health = new HealthAction(healer, addedPoints);
        new HealthEvent(health, allie).publishOn(bus);
        assertThat(allie.health().points()).isEqualTo(pointsBeforeHealth + addedPoints);
    }


    @After
    public void clearBus(){
        EventBus.destroy();
    }


    private Character anyCharacter() {
        return CharacterTestBuilder.newMeleeFighterWith(CharacterTestBuilder.maxHealth());
    }

}

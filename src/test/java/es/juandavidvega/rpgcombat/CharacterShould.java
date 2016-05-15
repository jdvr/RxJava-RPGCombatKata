package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.character.Character;

import es.juandavidvega.rpgcombat.character.Health;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterShould {


    public static final Double HIGHEST_DAMAGE = 1001d;

    @Test
    public void
    drop_health_to_zero_when_damage_is_higher_than_health() {
        Character target = CharacterTestBuilder.newCharacterWith(CharacterTestBuilder.maxHealth());
        assertThat(target.isAlive()).isTrue();
        target.receive(HIGHEST_DAMAGE);
        assertThat(target.isAlive()).isFalse();
        assertThat(target.health().points()).isEqualTo(Health.MIN_HEALTH_POINTS);
    }

    @Test
    public void
    recover_health_after_been_healed() {
        double healthPoints = 500.0;
        Character character = CharacterTestBuilder.newCharacterWith(CharacterTestBuilder.customHealth(healthPoints));
        character.health(healthPoints);
        assertThat(character.health().points()).isEqualTo(Health.MAX_HEALTH_POINTS);
    }

    @Test
    public void
    keep_dead_when_was_dead_before_be_health() {
        Character character = CharacterTestBuilder.newCharacterWith(CharacterTestBuilder.minHealth());
        assertThat(character.isAlive()).isFalse();
        character.health(Health.MAX_HEALTH_POINTS);
        assertThat(character.health().points()).isEqualTo(Health.MIN_HEALTH_POINTS);
        assertThat(character.isAlive()).isFalse();
    }

    @Test
    public void
    keep_same_health_after_be_healed_when_heal_is_Max_heal() {
        Character character = CharacterTestBuilder.newCharacterWith(CharacterTestBuilder.maxHealth());
        character.health(500.0);
        assertThat(character.health().points()).isEqualTo(Health.MAX_HEALTH_POINTS);
    }


}

package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.character.Character;

import es.juandavidvega.rpgcombat.character.Health;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterShould {


    public static final Integer HIGHEST_DAMAGE = 1001;

    @Test
    public void
    drop_health_to_zero_when_damage_is_higher_than_health() {
        Character target = newCharacterWith(maxHealth());
        assertThat(target.isAlive()).isTrue();
        target.receive(HIGHEST_DAMAGE);
        assertThat(target.isAlive()).isFalse();
        assertThat(target.health().points()).isEqualTo(Health.MIN_HEALTH_POINTS);
    }

    @Test
    public void
    recover_health_after_been_healed() {
        int healthPoints = 500;
        Character character = newCharacterWith(customHealth(healthPoints));
        character.health(healthPoints);
        assertThat(character.health().points()).isEqualTo(Health.MAX_HEALTH_POINTS);
    }

    @Test
    public void
    keep_dead_when_was_dead_before_be_health() {
        Character character = newCharacterWith(minHealth());
        assertThat(character.isAlive()).isFalse();
        character.health(Health.MAX_HEALTH_POINTS);
        assertThat(character.health().points()).isEqualTo(Health.MIN_HEALTH_POINTS);
        assertThat(character.isAlive()).isFalse();
    }

    @Test
    public void
    keep_same_health_after_be_healed_when_heal_is_Max_heal() {
        Character character = newCharacterWith(maxHealth());
        character.health(500);
        assertThat(character.health().points()).isEqualTo(Health.MAX_HEALTH_POINTS);
    }

    private Character newCharacterWith(Health health) {
        return new Character(health);
    }

    private Health customHealth(int customHealLevel) {
        return new Health(customHealLevel);
    }

    private Health minHealth() {
        return customHealth(Health.MIN_HEALTH_POINTS);
    }

    private Health maxHealth() {
        return customHealth(Health.MAX_HEALTH_POINTS);
    }


}

package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.character.Character;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterShould {

    private static final Integer START_HEALTH = 1000;
    private static final Integer MIN_HEALTH = 0;
    private static final Integer HIGHEST_DAMAGE = 1001;

    @Test
    public void
    drop_health_to_zero_when_damage_is_higher_than_health() {
        Character target = new Character(START_HEALTH);
        assertThat(target.isAlive()).isTrue();
        target.receive(HIGHEST_DAMAGE);
        assertThat(target.isAlive()).isFalse();
        assertThat(target.health()).isEqualTo(MIN_HEALTH);
    }

    @Test
    public void
    recover_health_after_been_healed() {
        Character character = new Character(500);
        assertThat(character.health()).isEqualTo(500);
        character.health(START_HEALTH);
        assertThat(character.health()).isEqualTo(START_HEALTH + 500);
    }

    @Test
    public void
    keep_dead_when_was_dead_before_be_health() {
        Character character = new Character(MIN_HEALTH);
        assertThat(character.isAlive()).isFalse();
        character.health(START_HEALTH);
        assertThat(character.health()).isEqualTo(MIN_HEALTH);
        assertThat(character.isAlive()).isFalse();
    }


}

package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.character.RPGCharacter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterShould {

    private static final Integer START_HEALTH = 1000;
    private static final Integer MIN_HEALTH = 1000;
    private static final Integer HIGHEST_DAMAGE = 1001;

    @Test
    public void
    drop_health_to_zero_when_damage_is_higher_than_health() {
        RPGCharacter target = new RPGCharacter(START_HEALTH);
        target.receive(HIGHEST_DAMAGE);
        assertThat(target.isAlive()).isTrue();
        assertThat(target.health()).isEqualTo(MIN_HEALTH);
    }


}

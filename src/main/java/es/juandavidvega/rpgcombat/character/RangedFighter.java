package es.juandavidvega.rpgcombat.character;

public class RangedFighter extends Character {


    public RangedFighter(Health health, Integer level) {
        super(health, level);
    }

    @Override
    public Integer range() {
        return 20;
    }
}

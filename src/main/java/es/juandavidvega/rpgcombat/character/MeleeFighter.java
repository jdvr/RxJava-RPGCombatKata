package es.juandavidvega.rpgcombat.character;

public class MeleeFighter extends Character {

    public MeleeFighter(Health health, Integer level) {
        super(health, level);
    }

    @Override
    public Integer range() {
        return 2;
    }
}

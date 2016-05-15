package es.juandavidvega.rpgcombat.character;

public class RPGCharacter {

    private Integer health;

    public RPGCharacter(Integer health) {

        this.health = health;
    }

    public void receive(Integer damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public Integer health() {
        return health;
    }
}

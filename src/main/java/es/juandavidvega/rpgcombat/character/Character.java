package es.juandavidvega.rpgcombat.character;

public class Character {

    private Integer health;

    public Character(Integer health) {
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

    public void health(Integer health) {

    }
}

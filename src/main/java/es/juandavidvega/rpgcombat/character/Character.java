package es.juandavidvega.rpgcombat.character;

public class Character {

    private Health health;

    public Character(Health health) {
        this.health = health;
    }

    public void receive(Integer damage) {
        health.subtract(damage);
    }

    public boolean isAlive() {
        return health.isAlive();
    }

    public Health health() {
        return health;
    }

    public void health(Integer health) {
        this.health.add(health);
    }
}

package es.juandavidvega.rpgcombat.character;

public class RPGCharacter {

    private Integer health;

    public RPGCharacter(Integer health) {

        this.health = health;
    }

    public void receive(Integer damage) {

    }

    public boolean isAlive() {
        return false;
    }

    public Integer health() {
        return null;
    }
}

package es.juandavidvega.rpgcombat.props;


import es.juandavidvega.rpgcombat.character.Health;

public class House {

    private final Health health;

    public House(Health health) {
        this.health = health;
    }

    public Health health() {
        return health;
    }
}

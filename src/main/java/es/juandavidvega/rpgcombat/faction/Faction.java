package es.juandavidvega.rpgcombat.faction;

import es.juandavidvega.rpgcombat.character.Character;

import java.util.ArrayList;
import java.util.List;

public class Faction {

    private final List<Character> characters = new ArrayList<>();
    private final String name;

    public Faction(String name) {
        this.name = name;
    }

    public Integer size() {
        return characters.size();
    }

    public String name() {
        return name;
    }

    public List<Character> characters() {
        return characters;
    }
}

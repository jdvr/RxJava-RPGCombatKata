package es.juandavidvega.rpgcombat.engine.events;

import es.juandavidvega.rpgcombat.acctions.Attack;
import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.GameEvent;

public class AttackEvent implements GameEvent{
    private Attack attack;
    private Character target;

    public AttackEvent(Attack attack, Character target) {
        this.attack = attack;
        this.target = target;
    }

    public Boolean areDifferentCharacters() {
        return attack.attacker() != target;
    }

    public Integer points () {
        return attack.points();
    }

    public Character target (){
        return  target;
    }

    @Override
    public GameEvents type() {
        return GameEvents.Attack;
    }
}

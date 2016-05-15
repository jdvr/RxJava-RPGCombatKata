package es.juandavidvega.rpgcombat.engine.events;

import es.juandavidvega.rpgcombat.engine.acctions.Attack;
import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.Event;

public class AttackEvent implements Event {
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
    public EventTypes type() {
        return EventTypes.Attack;
    }

    public boolean attackerIsAtLeastFiveLevelAboveTarget() {
        return target.level() - attack.attacker().level() >= 5;
    }
}

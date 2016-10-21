package es.juandavidvega.rpgcombat.engine.events.game;

import es.juandavidvega.rpgcombat.engine.acctions.Attack;
import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.Event;
import es.juandavidvega.rpgcombat.engine.events.EventType;

public class AttackEvent implements Event {
    public static final int DAMAGE_CHANGE_LEVEL_DIFFERENCE = 5;
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
    public EventType type() {
        return EventType.Attack;
    }

    public boolean attackerIsAtLeastFiveLevelAboveTarget() {
        return target.level() - attack.attacker().level() >= DAMAGE_CHANGE_LEVEL_DIFFERENCE;
    }

    public boolean attackerIsAtLeastFiveLevelBelowTarget() {
        return attack.attacker().level() - target.level() >= DAMAGE_CHANGE_LEVEL_DIFFERENCE;
    }

    public Character attacker() {
        return attack.attacker();
    }

    public Boolean areAllies() {
        return attacker().isAllieOf(target);
    }

    public Boolean areNotAllies() {
        return !attacker().isAllieOf(target);
    }

}

package es.juandavidvega.rpgcombat.engine.events.character;

import es.juandavidvega.rpgcombat.engine.events.game.AttackEvent;

public class DamageEventPointsCalculator {

    private AttackEvent attackEvent;

    public DamageEventPointsCalculator(AttackEvent attackEvent){
        this.attackEvent = attackEvent;
    }

    public Double calculate(){
        double damage = attackEvent.points();
        if(attackEvent.attackerIsAtLeastFiveLevelAboveTarget()) damage = fiftyPercentOf(damage);
        if(attackEvent.attackerIsAtLeastFiveLevelBelowTarget()) damage += fiftyPercentOf(damage);
        return damage;
    }

    private double fiftyPercentOf(double damage) {
        return damage * 0.5;
    }
}

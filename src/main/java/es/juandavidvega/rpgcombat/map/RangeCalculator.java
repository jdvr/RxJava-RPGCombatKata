package es.juandavidvega.rpgcombat.map;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.props.House;

/**
 * Created by jdvr on 15/05/16.
 */
public interface RangeCalculator {
    public Integer rangeBetween(Character attacker, Character enemy);
    public Integer rangeBetween(Character attacker, House props);
}

package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.EventBus;
import es.juandavidvega.rpgcombat.engine.events.faction.JoinFactionEvent;
import es.juandavidvega.rpgcombat.faction.Faction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class FactionsManagerShould {

    private  EventBus bus;

    @Before
    public void setUp(){
        bus = EventBus.get();
    }

    @Test
    public void
    allow_any_character_to_join_one_faction (){
        Faction faction = new Faction("exampleFaction");
        Character character = CharacterTestBuilder.newMeleeFighterWith(CharacterTestBuilder.maxHealth());
        new JoinFactionEvent(faction, character).publishOn(bus);
        assertThat(faction.size()).isEqualTo(1);
        assertThat(character.factions().size()).isEqualTo(1);
        assertThat(character.factions().get(0).name()).isEqualTo("exampleFaction");
        assertThat(faction.characters()).contains(character);
    }


    @After
    public void clearBus(){
        EventBus.destroy();
    }
}

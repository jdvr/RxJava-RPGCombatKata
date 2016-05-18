package es.juandavidvega.rpgcombat;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.EventBus;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;
import es.juandavidvega.rpgcombat.engine.events.faction.FactionEvent;
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
        new FactionEvent(faction, character, EventTypes.JoinFaction).publishOn();
        assertThat(faction.size()).isEqualTo(1);
        assertThat(character.factions().size()).isEqualTo(1);
        assertThat(character.factions()).contains(faction);
        assertThat(faction.characters()).contains(character);
    }

    @Test
    public void
    allow_any_character_to_leave_one_faction (){
        Faction faction = new Faction("exampleFaction");
        Character character = CharacterTestBuilder.newMeleeFighterWith(CharacterTestBuilder.maxHealth());
        faction.addCharacter(character);
        character.addFaction(faction);
        new FactionEvent(faction, character, EventTypes.LeaveFaction).publishOn();
        assertThat(faction.size()).isEqualTo(0);
        assertThat(character.factions().size()).isEqualTo(0);
    }


    @After
    public void clearBus(){
        EventBus.destroy();
    }
}

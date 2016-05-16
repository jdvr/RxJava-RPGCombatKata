package es.juandavidvega.rpgcombat.faction;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.EventBus;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;
import es.juandavidvega.rpgcombat.engine.events.GameEventChecker;
import es.juandavidvega.rpgcombat.engine.events.faction.FactionEvent;
import es.juandavidvega.rpgcombat.engine.subscription.Subscriptions;
import rx.Subscription;

import java.util.ArrayList;
import java.util.List;

public class Faction {

    private final List<Character> characters = new ArrayList<>();
    private final String name;
    private Subscriptions subscriptions = new Subscriptions();

    public Faction(String name) {
        this.name = name;
        listenJoinFactions();
        listenLeaveFactions();
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

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public boolean isThisFaction(FactionEvent joinFactionEvent) {
        return joinFactionEvent.faction().equals(this);
    }

    public void joinFaction(FactionEvent factionEvent) {
        addCharacter(factionEvent.target());
    }

    public void leaveFaction(FactionEvent factionEvent) {
        characters.remove(factionEvent.target());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Faction && this.equals((Faction) obj);
    }

    public boolean equals(Faction faction) {
        return this.name.equals(faction.name());
    }

    private EventBus getEventBus() {
        return EventBus.get();
    }


    private void listenJoinFactions() {
        Subscription subscribe = getEventBus().toObservable()
                .filter(event -> new GameEventChecker().isFactionJoin(event))
                .map(gameEvent -> (FactionEvent) gameEvent)
                .filter(this::isThisFaction)
                .subscribe(this::joinFaction);
        subscriptions.add(EventTypes.JoinFaction, subscribe);
    }

    private void listenLeaveFactions() {
        Subscription subscribe = getEventBus().toObservable()
                .filter(event -> new GameEventChecker().isLeaveFaction(event))
                .map(gameEvent -> (FactionEvent) gameEvent)
                .filter(this::isThisFaction)
                .subscribe(this::leaveFaction);
        subscriptions.add(EventTypes.LeaveFaction, subscribe);
    }
}

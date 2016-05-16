package es.juandavidvega.rpgcombat.faction;

import es.juandavidvega.rpgcombat.character.Character;
import es.juandavidvega.rpgcombat.engine.events.EventBus;
import es.juandavidvega.rpgcombat.engine.events.EventTypes;
import es.juandavidvega.rpgcombat.engine.events.GameEventChecker;
import es.juandavidvega.rpgcombat.engine.events.faction.JoinFactionEvent;
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

    private void listenJoinFactions() {
        Subscription subscribe = getEventBus().toObservable()
                .filter(event -> new GameEventChecker().isFactionJoin(event))
                .map(gameEvent -> (JoinFactionEvent) gameEvent)
                .filter(this::isThisFaction)
                .subscribe(this::joinFaction);
        subscriptions.add(EventTypes.JoinFaction, subscribe);
    }

    public void joinFaction(JoinFactionEvent joinFactionEvent) {
        characters.add(joinFactionEvent.target());
    }

    public boolean isThisFaction(JoinFactionEvent joinFactionEvent) {
        return joinFactionEvent.faction().equals(this);
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
}

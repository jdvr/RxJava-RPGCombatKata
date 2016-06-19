package es.juandavidvega.rpgcombat.engine.events;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class EventBus {
    private static EventBus bus;
    private final Subject<Event, Event> subject = new SerializedSubject<>(PublishSubject.create());

    public void send(Event event) {
        subject.onNext(event);
    }

    public <T> Observable<T>  streamOf(EventType type, Class<T> targetEvent) {
        Func1<Event, T> castToGeneric = filteredEvent -> (T) filteredEvent;
        return toObservable().filter(event -> event.is(type)).map(castToGeneric);
    }

    public static EventBus get(){
        return bus == null ? createBus() : bus;
    }

    private Observable<Event> toObservable() {
        return subject;
    }

    private static EventBus createBus() {
        bus = new EventBus();
        return bus;
    }

    public static void destroy() {
        bus = null;
    }
}

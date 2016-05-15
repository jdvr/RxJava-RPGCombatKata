package es.juandavidvega.rpgcombat.engine.events;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class EventBus {
    private static EventBus bus;
    private final Subject<Object, Object> subject = new SerializedSubject<>(PublishSubject.create());

    public void send(Event event) {
        subject.onNext(event);
    }

    public Observable<Object> toObservable() {
        return subject;
    }

    public static EventBus get(){
        return bus == null ? createBus() : bus;
    }

    private static EventBus createBus() {
        bus = new EventBus();
        return bus;
    }

    public static void destroy() {
        bus = null;
    }
}

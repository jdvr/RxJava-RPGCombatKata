package es.juandavidvega.rpgcombat.engine;

import rx.Observable;
import rx.subjects.AsyncSubject;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

public class EventBus {
    private static EventBus bus;
    private final Subject<Object, Object> subject = new SerializedSubject<>(PublishSubject.create());

    public void send(GameEvent event) {
        subject.onNext(event);
    }

    public Observable<Object> toObserverable() {
        return subject;
    }

    public static EventBus get(){
        return bus == null ? createBus() : bus;
    }

    private static EventBus createBus() {
        bus = new EventBus();
        return bus;
    }

}

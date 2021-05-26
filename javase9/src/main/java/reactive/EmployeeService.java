package reactive;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Flow;

public class EmployeeService implements Flow.Publisher<EmployeeHasCreatedEvent> {

    private Queue<EmployeeHasCreatedEvent> events = new ArrayDeque<>();

    public void createEmployee(String name) {
        events.add(new EmployeeHasCreatedEvent(String.format("Employee has created: %s", name)));
    }

    @Override
    public void subscribe(Flow.Subscriber<? super EmployeeHasCreatedEvent> subscriber) {
        EmployeeHasCreatedSubscription subscription = new EmployeeHasCreatedSubscription(subscriber);
        subscriber.onSubscribe(subscription);
    }

    public class EmployeeHasCreatedSubscription implements Flow.Subscription {

        Flow.Subscriber<? super EmployeeHasCreatedEvent> subscriber;

        public EmployeeHasCreatedSubscription(Flow.Subscriber<? super EmployeeHasCreatedEvent> subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long n) {
            int i = 0;
            while (!events.isEmpty() && i < n) {
                subscriber.onNext(events.poll());
                i++;
            }
            if (events.isEmpty()) {
                subscriber.onComplete();
            }
        }

        @Override
        public void cancel() {

        }
    }
}

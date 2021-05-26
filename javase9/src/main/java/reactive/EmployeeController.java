package reactive;

import java.util.concurrent.Flow;

public class EmployeeController implements Flow.Subscriber<EmployeeHasCreatedEvent> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    public void request(int n) {
        subscription.request(n);
    }

    @Override
    public void onNext(EmployeeHasCreatedEvent item) {
        System.out.println(item.getMessage());
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {
        System.out.println("No more events");
    }
}

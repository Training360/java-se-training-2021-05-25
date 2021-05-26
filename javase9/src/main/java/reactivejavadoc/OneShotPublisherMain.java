package reactivejavadoc;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class OneShotPublisherMain {

    public static void main(String[] args) throws InterruptedException {
        OneShotPublisher publisher = new OneShotPublisher();

        CountDownLatch latch = new CountDownLatch(1);

        SampleSubscriber<Boolean> subscriber =
                new SampleSubscriber<>(10, b -> {System.out.println(b); latch.countDown();});

        publisher.subscribe(subscriber);

        latch.await();

    }
}

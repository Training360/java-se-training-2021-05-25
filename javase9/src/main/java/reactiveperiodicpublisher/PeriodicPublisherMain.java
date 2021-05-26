package reactiveperiodicpublisher;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PeriodicPublisherMain {

    public static void main(String[] args) {
        PeriodicPublisher<String> publisher = new PeriodicPublisher<>(
                Executors.newFixedThreadPool(2),
                10,
                () -> {
                    System.out.println("call supplier");
                    return "Hello SubmissionPublisher!";
                    },
                1,
                TimeUnit.SECONDS
        );

        publisher.subscribe(
                new TransformProcessor<>(Executors.newFixedThreadPool(2),
                        10,
                        s -> {
                            System.out.println("Call processor 1");
                            System.out.println(s);
                            return s.toUpperCase();
                        })
        );

        publisher.subscribe(
                new TransformProcessor<>(Executors.newFixedThreadPool(2),
                        10,
                        s -> {
                            System.out.println("Call processor 2");
                            System.out.println(s);
                            return s.toUpperCase();
                        })
        );
    }
}

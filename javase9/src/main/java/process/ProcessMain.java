package process;

import java.util.concurrent.CountDownLatch;

public class ProcessMain {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        ProcessHandle
                .allProcesses()
                .map(processHandle -> processHandle.info())
                .map(ProcessHandle.Info::toString)
                .forEach(System.out::println);

        ProcessHandle.allProcesses()
                .filter(p -> filterByName(p, "notepad"))
                .forEach(p -> p.onExit().thenApply((ph) -> {
                    System.out.println("Exited");
                    latch.countDown();
                    return ph;
                }));

        latch.await();
    }

    public static boolean filterByName(ProcessHandle processHandle, String name) {
        return processHandle.info().command().orElseGet(() -> "").toLowerCase().contains(name);
    }
}

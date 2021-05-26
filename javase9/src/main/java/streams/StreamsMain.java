package streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsMain {

    public static void main(String[] args) {
        System.out.println("Take");

        System.out.println(IntStream.range(0, 10)
                .takeWhile(i -> i < 5)
                .mapToObj(i -> i)
                .collect(Collectors.toList()));

        System.out.println("Drop");

        System.out.println(IntStream.range(0, 10)
                .dropWhile(i -> i < 5)
                .mapToObj(i -> i)
                .collect(Collectors.toList()));

        String name = null;
        List<String> names = Stream.ofNullable(name).collect(Collectors.toList());
        System.out.println(names);

        System.out.println(Stream.iterate(0, i -> i < 10, i -> i + 1)
            .collect(Collectors.toList()));

        List<Optional<Integer>> numbers =
                List.of(
                        Optional.of(0),
                        Optional.of(1),
                        Optional.empty(),
                        Optional.of(2),
                        Optional.empty()
                        );

        System.out.println(numbers.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.toList()));

        System.out.println(Optional.ofNullable(name).or(() -> Optional.of("anonymous")).get());

        Optional.ofNullable(name)
                .ifPresentOrElse(System.out::println, () -> System.out.println("empty"));
    }
}

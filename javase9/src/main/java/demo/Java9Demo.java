package demo;

import orelsethrow.Employee;

import javax.management.openmbean.OpenDataException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java9Demo {

    public static void main(String[] args) {
        // File
        //Path file = Paths.get("files");

        Path file = Path.of("files");

        Stream.iterate(5, i -> i < 20, i -> i + 2)
            .forEach(System.out::println);

        Optional.of("Foo").stream().forEach((o) -> System.out.println("Alma"));

        Scanner scanner = new Scanner("alma,korte,barack").useDelimiter(",");

        //scanner.tokens().forEach(System.out::println);

        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }

        if (Optional.empty().isEmpty()) {

        }
    }

    public void copy(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[1024];
        int size ;
        while ((size = is.read(buffer, 0, buffer.length)) > 0) {
            os.write(buffer, 0, size);
        }
    }

    public List<String> findByIds(List<Long> ids) {
        return ids.stream()
//                .flatMap(id -> findById(id).stream())
                .map(this::findById)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());

    }

    public Optional<String> findById(long id) {
        return Optional.empty();
    }
}

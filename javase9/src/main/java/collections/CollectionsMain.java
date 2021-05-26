package collections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionsMain {

    public static void main(String[] args) {
        List<String> names = List.of("John Doe", "Jack Doe", "Jane Doe");
        // names.set(1, "Jack Jack Doe"); // UnsupportedOperationException
        // names.add("John Smith"); // UnsupportedOperationException

        List<String> nicks = Arrays.asList("John", "Little John", "Johnny");
        //nicks.add("JJ"); // UnsupportedOperationException
        nicks.set(1, "Little Little John"); // Valid
        System.out.println(nicks);

        Map<Long, String> employees = Map.of(1L, "John Doe", 2L, "Jack Doe", 3L, "Jane Doe");
        System.out.println(employees);

        employees = Map.ofEntries(
                Map.entry(1L, "John Doe"),
                Map.entry(2L, "Jack Doe"),
                Map.entry(3L, "Jane Doe")
                );

        System.out.println(employees);

//        Map<Long, String> emp = new HashMap<>(){{
//                put(1L, "John Doe");
//        }};

    }
}

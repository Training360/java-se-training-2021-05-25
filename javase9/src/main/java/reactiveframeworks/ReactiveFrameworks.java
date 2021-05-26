package reactiveframeworks;

import io.reactivex.rxjava3.core.Flowable;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReactiveFrameworks {

    public static void main(String[] args) {
        List<Employee> employees =
                List.of(
                        new Employee("John Doe", 2000),
                        new Employee("Jane Doe", 2001),
                        new Employee("Jack Doe", 2002)
                        );

        Flowable.fromIterable(employees)
                .filter(employee -> employee.getYearOfBirth() >= 2001)
                .map(Employee::getName)
                .map(String::toUpperCase)
                .subscribe(System.out::println);

        Flux.fromIterable(employees)
                .filter(employee -> employee.getYearOfBirth() >= 2001)
                .map(Employee::getName)
                .map(String::toUpperCase)
                .subscribe(System.out::println);

        Flux.from(Flowable.fromIterable(employees)
                .filter(employee -> employee.getYearOfBirth() >= 2001)
                .map(Employee::getName))
                .map(String::toUpperCase)
                .subscribe(System.out::println);

    }
}

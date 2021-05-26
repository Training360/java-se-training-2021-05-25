package reactive;

import java.util.stream.IntStream;

public class EmployeeMain {

    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        IntStream.range(0, 10)
                .forEach(i -> service.createEmployee("John Doe " + i));

        EmployeeController controller = new EmployeeController();
        service.subscribe(controller);

        controller.request(3);
        controller.request(3);
        controller.request(6);


    }
}

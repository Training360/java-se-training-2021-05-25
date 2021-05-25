package employees.frontend;

import employees.backend.EmployeesService;

import java.util.Iterator;
import java.util.ServiceLoader;

public class EmployeesController {

    public static void main(String[] args) {
        ServiceLoader<EmployeesService> bookmarkServices = ServiceLoader.load(EmployeesService.class);
        Iterator<EmployeesService> i = bookmarkServices.iterator();
        EmployeesService employeesService = null;
        if (i.hasNext()) {
            employeesService = i.next();
        }
        else {
            throw new IllegalStateException("Service not found");
        }

        employeesService.save("John Doe");
        employeesService.save("Jack Doe");

        System.out.println(employeesService.findAll());
    }
}

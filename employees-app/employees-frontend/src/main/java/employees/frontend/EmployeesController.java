package employees.frontend;

import employees.backend.EmployeesService;

public class EmployeesController {

    public static void main(String[] args) {
        EmployeesService employeesService = new EmployeesService();
        employeesService.save("John Doe");
        employeesService.save("Jack Doe");

        System.out.println(employeesService.findAll());
    }
}

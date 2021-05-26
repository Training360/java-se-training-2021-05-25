package employees.impl;

import employees.backend.CreateEmployeeCommand;
import employees.backend.EmployeeDto;
import employees.backend.EmployeesService;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class InMemoryEmployeesService implements EmployeesService {

    private List<Employee> employees =
    new ArrayList<>(
            List.of(new Employee("John Doe"), new Employee("Jack Doe"))
    );

    public EmployeeDto save(CreateEmployeeCommand command) {
        var employee = new Employee(command.getName());
        employees.add(employee);
        return new EmployeeDto(employee.getName());
    }

    public List<EmployeeDto> findAll() {
        return employees.stream().map(e -> new EmployeeDto(e.getName())).collect(Collectors.toUnmodifiableList());
    }
}

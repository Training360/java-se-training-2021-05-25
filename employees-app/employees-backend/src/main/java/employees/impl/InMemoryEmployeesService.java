package employees.impl;

import employees.backend.EmployeesService;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEmployeesService implements EmployeesService {

    private List<String> employees = new ArrayList<>();

    public String save(String name) {
        employees.add(name);
        return name;
    }

    public List<String> findAll() {
        return List.copyOf(employees);
    }
}

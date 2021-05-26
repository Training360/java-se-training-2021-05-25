package employees.backend;

import java.util.List;

public interface EmployeesService {

    EmployeeDto save(CreateEmployeeCommand command);

    List<EmployeeDto> findAll();
}

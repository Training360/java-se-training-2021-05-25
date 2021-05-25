package employees.backend;

import java.util.List;

public interface EmployeesService {

    String save(String name);

    List<String> findAll();
}

package orelsethrow;

import java.util.Optional;

public class EmployeeService {

    public Employee findById(long id) {
        return findEmployeeById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Employee has not found with id %s", id)));
    }

    public Optional<Employee> findEmployeeById(long id) {
        return Optional.empty();
    }
}

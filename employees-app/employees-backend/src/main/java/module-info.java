module employees.backend {

    exports employees.backend to employees.frontend;
    provides employees.backend.EmployeesService with employees.impl.InMemoryEmployeesService;
}
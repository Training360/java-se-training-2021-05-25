module employees.frontend {

    requires employees.backend;

    uses employees.backend.EmployeesService;

    requires javaee.api;
//    requires jakarta.jakartaee.api;
}
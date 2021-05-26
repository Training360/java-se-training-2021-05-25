package employees.frontend;

import employees.backend.CreateEmployeeCommand;
import employees.backend.EmployeeDto;
import employees.backend.EmployeesService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("employees")
public class EmployeesResource {

    @Inject
    private EmployeesService employeesService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDto> listEmployees() {
        return employeesService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployee(CreateEmployeeCommand command) {
        var employee = employeesService.save(command);
        return Response.status(201).entity(employee).build();
    }

}

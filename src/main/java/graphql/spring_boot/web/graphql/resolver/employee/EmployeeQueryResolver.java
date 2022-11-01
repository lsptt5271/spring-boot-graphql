package graphql.spring_boot.web.graphql.resolver.employee;

import java.util.List;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.spring_boot.web.graphql.type.Employee;
import graphql.spring_boot.web.service.EmployeeService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EmployeeQueryResolver implements GraphQLQueryResolver {

    private final EmployeeService employeeService;

    public List<Employee> getEmployees() {

        return employeeService.getEmployees();

    }

}

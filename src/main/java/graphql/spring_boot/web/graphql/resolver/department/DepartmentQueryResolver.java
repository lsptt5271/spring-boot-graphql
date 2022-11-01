package graphql.spring_boot.web.graphql.resolver.department;

import java.util.List;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.spring_boot.web.graphql.type.Department;
import graphql.spring_boot.web.service.DepartmentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DepartmentQueryResolver implements GraphQLQueryResolver {

    private final DepartmentService departmentService;

    public List<Department> getDepartments() {

        return departmentService.getDepartments();

    }

}
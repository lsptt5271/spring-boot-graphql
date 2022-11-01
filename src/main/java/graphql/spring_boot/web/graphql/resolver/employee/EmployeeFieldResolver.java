package graphql.spring_boot.web.graphql.resolver.employee;

import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.spring_boot.web.dataloader.DataLoaderRegistryFactory;
import graphql.spring_boot.web.graphql.type.Department;
import graphql.spring_boot.web.graphql.type.Employee;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EmployeeFieldResolver implements GraphQLResolver<Employee> {

    public CompletableFuture<Department> getDepartment(Employee source, DataFetchingEnvironment environment) {

        DataLoader<Integer, Department> dataLoader = environment
                .getDataLoader(DataLoaderRegistryFactory.DepartmentDataLoader);
        return dataLoader.load(source.getDepartmentId());

    }

}

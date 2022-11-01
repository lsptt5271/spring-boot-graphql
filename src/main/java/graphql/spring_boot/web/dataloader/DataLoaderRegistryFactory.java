package graphql.spring_boot.web.dataloader;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.dataloader.DataLoaderFactory;
import org.dataloader.DataLoaderOptions;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import graphql.spring_boot.web.graphql.type.Department;
import graphql.spring_boot.web.service.DepartmentService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DataLoaderRegistryFactory {

    public static final String DepartmentDataLoader = "department";

    private final DepartmentService departmentService;

    public DataLoaderRegistry create() {

        DataLoaderRegistry registry = new DataLoaderRegistry();
        registry.register(DepartmentDataLoader, createDepartmentDataLoader());

        return registry;

    }

    public DataLoader<Integer, Department> createDepartmentDataLoader() {

        return DataLoaderFactory.newMappedDataLoader((Set<Integer> keys) -> {
            return CompletableFuture.completedFuture(departmentService.getDepartmentMapFor(keys));
        }, DataLoaderOptions.newOptions().setCachingEnabled(false));

    }

}

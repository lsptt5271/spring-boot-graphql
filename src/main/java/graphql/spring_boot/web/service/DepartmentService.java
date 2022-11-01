package graphql.spring_boot.web.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import graphql.spring_boot.web.entity.DepartmentEntity;
import graphql.spring_boot.web.graphql.type.Department;
import graphql.spring_boot.web.repository.DepartmentRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {

        return departmentRepository.findAll().stream().map(this::build).collect(Collectors.toList());

    }

    public Map<Integer, Department> getDepartmentMapFor(Set<Integer> departmentIds) {

        Map<Integer, Department> map = new ConcurrentHashMap<>();
        departmentRepository.findByIdIn(departmentIds).stream().forEach(e -> {
            Department department = build(e);
            map.put(department.getId(), department);
        });

        return map;

    }

    private Department build(DepartmentEntity entity) {

        return Department.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

    }

}

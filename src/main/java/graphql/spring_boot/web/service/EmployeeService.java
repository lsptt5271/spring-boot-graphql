package graphql.spring_boot.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import graphql.spring_boot.web.entity.EmployeeEntity;
import graphql.spring_boot.web.graphql.type.Employee;
import graphql.spring_boot.web.repository.EmployeeRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {

        return employeeRepository.findAll().stream().map(this::build).collect(Collectors.toList());

    }

    private Employee build(EmployeeEntity entity) {

        return Employee.builder()
                .id(entity.getId())
                .name(entity.getName())
                .departmentId(entity.getDepartmentId())
                .build();

    }

}

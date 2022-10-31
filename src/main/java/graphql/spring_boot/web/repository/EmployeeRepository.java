package graphql.spring_boot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import graphql.spring_boot.web.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
}

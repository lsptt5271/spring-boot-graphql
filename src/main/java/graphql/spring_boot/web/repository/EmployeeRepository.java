package graphql.spring_boot.web.repository;

import graphql.spring_boot.web.entity.EmployeeEntity;
import graphql.spring_boot.web.repository.base.ExtendedRepository;

public interface EmployeeRepository extends ExtendedRepository<EmployeeEntity, Integer> {
}

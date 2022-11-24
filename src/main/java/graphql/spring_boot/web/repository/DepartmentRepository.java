package graphql.spring_boot.web.repository;

import java.util.List;
import java.util.Set;

import graphql.spring_boot.web.entity.DepartmentEntity;
import graphql.spring_boot.web.repository.base.ExtendedRepository;

public interface DepartmentRepository extends ExtendedRepository<DepartmentEntity, Integer> {

    public List<DepartmentEntity> findByIdIn(Set<Integer> ids);

}

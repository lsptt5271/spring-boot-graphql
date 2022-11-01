package graphql.spring_boot.web.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import graphql.spring_boot.web.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

    public List<DepartmentEntity> findByIdIn(Set<Integer> ids);

}

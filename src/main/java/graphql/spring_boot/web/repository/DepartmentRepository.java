package graphql.spring_boot.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import graphql.spring_boot.web.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
}

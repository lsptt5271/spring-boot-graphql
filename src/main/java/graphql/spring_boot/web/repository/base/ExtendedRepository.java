package graphql.spring_boot.web.repository.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import graphql.spring_boot.web.entity.base.ExEntityGraph;

@NoRepositoryBean
public interface ExtendedRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    Optional<T> findById(ID id, ExEntityGraph<T> graph);

    Optional<T> findOne(Specification<T> spec, ExEntityGraph<T> graph);

    List<T> findAll(Specification<T> spec, ExEntityGraph<T> graph);

    List<T> findAll(Specification<T> spec, ExEntityGraph<T> graph, Sort sort);

    List<T> findAll(Specification<T> spec, ExEntityGraph<T> graph, Sort sort, int limit);

    List<T> findAll(Specification<T> spec, ExEntityGraph<T> graph, Sort sort, int offset, int limit);

    List<T> findAll(ExEntityGraph<T> graph);

    List<T> findAll(ExEntityGraph<T> graph, Sort sort);

    List<T> findAll(ExEntityGraph<T> graph, Sort sort, int limit);

    List<T> findAll(ExEntityGraph<T> graph, Sort sort, int offset, int limit);

}

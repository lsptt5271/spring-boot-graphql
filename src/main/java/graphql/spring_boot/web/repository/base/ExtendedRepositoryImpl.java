package graphql.spring_boot.web.repository.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import graphql.spring_boot.web.entity.base.ExEntityGraph;

public class ExtendedRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements ExtendedRepository<T, ID> {

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";
    private final EntityManager em;

    public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    @Override
    public Optional<T> findById(ID id, ExEntityGraph<T> graph) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);
        Map<String, Object> hints = new HashMap<>();
        hints.put(graph.key(), graph.value(em));
        return Optional.ofNullable(em.find(getDomainClass(), id, hints));
    }

    @Override
    public Optional<T> findOne(Specification<T> spec, ExEntityGraph<T> graph) {
        try {
            return Optional.of(getQuery(spec, graph, Sort.unsorted()).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll(Specification<T> spec, ExEntityGraph<T> graph) {
        return findAll(spec, graph, Sort.unsorted());
    }

    @Override
    public List<T> findAll(Specification<T> spec, ExEntityGraph<T> graph, Sort sort) {
        return findAll(spec, graph, sort, -1);
    }

    @Override
    public List<T> findAll(Specification<T> spec, ExEntityGraph<T> graph, Sort sort, int limit) {
        return findAll(spec, graph, sort, 0, limit);
    }

    @Override
    public List<T> findAll(Specification<T> spec, ExEntityGraph<T> graph, Sort sort, int offset, int limit) {
        TypedQuery<T> query = getQuery(spec, graph, sort);

        if (limit > 0) {
            query = query.setFirstResult(offset).setMaxResults(limit);
        }

        return query.getResultList();
    }

    @Override
    public List<T> findAll(ExEntityGraph<T> graph) {
        return findAll(null, graph);
    }

    @Override
    public List<T> findAll(ExEntityGraph<T> graph, Sort sort) {
        return findAll(null, graph, sort);
    }

    @Override
    public List<T> findAll(ExEntityGraph<T> graph, Sort sort, int limit) {
        return findAll(null, graph, sort, limit);
    }

    @Override
    public List<T> findAll(ExEntityGraph<T> graph, Sort sort, int offset, int limit) {
        return findAll(null, graph, sort, offset, limit);
    }

    protected TypedQuery<T> getQuery(@Nullable Specification<T> spec, ExEntityGraph<T> graph, Sort sort) {
        Class<T> domainClass = getDomainClass();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(domainClass);

        Root<T> root = applySpecificationToCriteria(spec, domainClass, query);
        query.select(root);

        if (sort.isSorted()) {
            query.orderBy(QueryUtils.toOrders(sort, root, builder));
        }

        TypedQuery<T> toReturn = em.createQuery(query);
        toReturn.setHint(graph.key(), graph.value(em));

        return toReturn;
    }

    private <S, U extends T> Root<U> applySpecificationToCriteria(@Nullable Specification<U> spec, Class<U> domainClass,
            CriteriaQuery<S> query) {
        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(query, "CriteriaQuery must not be null!");

        Root<U> root = query.from(domainClass);

        if (spec == null) {
            return root;
        }

        CriteriaBuilder builder = em.getCriteriaBuilder();
        Predicate predicate = spec.toPredicate(root, query, builder);

        if (predicate == null) {
            query.where(predicate);
        }

        return root;
    }

}

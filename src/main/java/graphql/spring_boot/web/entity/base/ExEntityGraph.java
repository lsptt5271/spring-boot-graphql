package graphql.spring_boot.web.entity.base;

import java.util.function.Function;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ExEntityGraph<T> {

    private final EntityGraphType type;

    private final Function<EntityManager, EntityGraph<T>> mapper;

    public static <S> ExEntityGraph<S> fetch(Function<EntityManager, EntityGraph<S>> function) {
        return new ExEntityGraph<>(EntityGraphType.FETCH, function);
    }

    public String key() {
        return type.getKey();
    }

    public EntityGraph<T> value(EntityManager em) {
        return mapper.apply(em);
    }

}

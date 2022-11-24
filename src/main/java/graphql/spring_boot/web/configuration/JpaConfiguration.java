package graphql.spring_boot.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import graphql.spring_boot.web.repository.base.ExtendedRepositoryImpl;

@Configuration
@EnableJpaRepositories(basePackages = {
        "graphql.spring_boot.web.repository" }, repositoryBaseClass = ExtendedRepositoryImpl.class)
public class JpaConfiguration {
}

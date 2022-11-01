package graphql.spring_boot.web.graphql.configulation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.schema.GraphQLScalarType;
import graphql.spring_boot.web.graphql.scalar.ExtendedScalar;

@Configuration
public class ScalarConfiguration {

    @Bean
    public GraphQLScalarType uploadScalarDefine() {
        return ExtendedScalar.UploadScalarType;
    }

}
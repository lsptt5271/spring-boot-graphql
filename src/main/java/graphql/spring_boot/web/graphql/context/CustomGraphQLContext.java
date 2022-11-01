package graphql.spring_boot.web.graphql.context;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.dataloader.DataLoaderRegistry;

import graphql.kickstart.servlet.context.GraphQLServletContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomGraphQLContext implements GraphQLServletContext {

    private final GraphQLServletContext context;

    @Override
    public Optional<Subject> getSubject() {
        return context.getSubject();
    }

    @Override
    public DataLoaderRegistry getDataLoaderRegistry() {
        return context.getDataLoaderRegistry();
    }

    @Override
    public List<Part> getFileParts() {
        return context.getFileParts();
    }

    @Override
    public Map<String, List<Part>> getParts() {
        return context.getParts();
    }

    @Override
    public HttpServletRequest getHttpServletRequest() {
        return context.getHttpServletRequest();
    }

    @Override
    public HttpServletResponse getHttpServletResponse() {
        return context.getHttpServletResponse();
    }

}

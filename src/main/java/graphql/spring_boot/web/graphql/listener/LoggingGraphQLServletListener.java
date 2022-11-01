package graphql.spring_boot.web.graphql.listener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import graphql.kickstart.servlet.core.GraphQLServletListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingGraphQLServletListener implements GraphQLServletListener {

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {

        StringBuilder sb = new StringBuilder();
        sb.append("[開始]");
        sb.append("[GraphQL]");

        log.info(sb.toString());

        return new RequestCallback() {
            @Override
            public void onFinally(HttpServletRequest request, HttpServletResponse response) {

                StringBuilder sb = new StringBuilder();
                sb.append("[終了]");
                sb.append("[GraphQL]");

                log.info(sb.toString());

            }
        };

    }

}
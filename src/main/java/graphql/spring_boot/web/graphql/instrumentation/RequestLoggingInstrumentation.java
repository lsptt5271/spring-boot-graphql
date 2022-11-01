package graphql.spring_boot.web.graphql.instrumentation;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

import org.springframework.stereotype.Component;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestLoggingInstrumentation extends SimpleInstrumentation {

    private final Clock clock = Clock.systemDefaultZone();

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {

        Instant start = Instant.now(clock);

        log.info("GraphQL: query:\r\n{}", parameters.getQuery());
        log.info("GraphQL: variables:\r\n{}", parameters.getVariables());

        return SimpleInstrumentationContext.whenCompleted((executionResult, throwable) -> {
            Duration duration = Duration.between(start, Instant.now(clock));
            if (Objects.isNull(throwable)) {
                log.info("GraphQL: execution completed successfully in {}", duration);
            } else {
                log.error("GraphQL: execution failed in {}", duration, throwable);
            }
        });

    }

}
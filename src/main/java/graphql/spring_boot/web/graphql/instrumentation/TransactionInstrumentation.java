package graphql.spring_boot.web.graphql.instrumentation;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecuteOperationParameters;
import graphql.language.OperationDefinition.Operation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TransactionInstrumentation extends SimpleInstrumentation {

    private final PlatformTransactionManager transactionManager;

    @Override
    public InstrumentationContext<ExecutionResult> beginExecuteOperation(
            InstrumentationExecuteOperationParameters parameters) {

        var operation = parameters.getExecutionContext().getOperationDefinition().getOperation();
        if (Operation.MUTATION.equals(operation)) {
            super.beginExecuteOperation(parameters);
        }

        TransactionTemplate tx = new TransactionTemplate(this.transactionManager);
        TransactionStatus status = this.transactionManager.getTransaction(tx);

        return SimpleInstrumentationContext.whenDispatched(codeToRun -> {
            ExecutionResult result = codeToRun.join();

            if (codeToRun.isCompletedExceptionally() || !result.getErrors().isEmpty()) {
                this.transactionManager.rollback(status);

                codeToRun.exceptionally(e -> {
                    throw new RuntimeException(e);
                });
            } else {
                this.transactionManager.commit(status);
            }
        });
    }

}

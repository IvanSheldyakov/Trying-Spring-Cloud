package pet.project.licensingservice.context;

import java.util.Objects;

public class ExecutionContextHolder {

    private static final ThreadLocal<ExecutionContext> EXECUTION_CONTEXT = new ThreadLocal<>();

    public static ExecutionContext getContext() {
        ExecutionContext context = EXECUTION_CONTEXT.get();
        if (Objects.isNull(context)) {
            EXECUTION_CONTEXT.set(new ExecutionContext());
        }
        return EXECUTION_CONTEXT.get();
    }

    public static void setExecutionContext(ExecutionContext context) {
        EXECUTION_CONTEXT.set(context);
    }
}

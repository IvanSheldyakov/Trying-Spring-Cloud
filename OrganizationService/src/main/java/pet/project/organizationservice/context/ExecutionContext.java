package pet.project.organizationservice.context;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;


@Getter
@Setter
@NoArgsConstructor
public class ExecutionContext {

    private Locale locale;

    private String correlationId;
}

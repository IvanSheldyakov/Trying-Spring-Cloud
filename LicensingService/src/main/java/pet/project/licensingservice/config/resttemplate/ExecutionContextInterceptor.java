package pet.project.licensingservice.config.resttemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import pet.project.licensingservice.constant.Headers;
import pet.project.licensingservice.context.ExecutionContextHolder;

import java.io.IOException;

public class ExecutionContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution
    ) throws IOException {
        HttpHeaders httpHeaders = request.getHeaders();
        httpHeaders.add(Headers.CORRELATION_ID, ExecutionContextHolder.getContext().getCorrelationId());
        return execution.execute(request, body);
    }
}

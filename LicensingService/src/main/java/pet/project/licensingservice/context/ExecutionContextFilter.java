package pet.project.licensingservice.context;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pet.project.licensingservice.constant.Headers;
import pet.project.licensingservice.utils.NullSafeUtils;

import java.io.IOException;
import java.util.Locale;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExecutionContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            ExecutionContext context = new ExecutionContext();

            String language = request.getHeader(Headers.ACCEPT_LANGUAGE);
            context.setLocale(NullSafeUtils.applyIfNotNull(language, Locale::of));

            ExecutionContextHolder.setExecutionContext(context);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e); //TODO кастомные исключения с кодами
        }
    }
}

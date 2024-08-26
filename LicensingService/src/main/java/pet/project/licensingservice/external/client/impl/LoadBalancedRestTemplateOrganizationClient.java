package pet.project.licensingservice.external.client.impl;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pet.project.licensingservice.external.api.model.Organization;
import pet.project.licensingservice.external.client.ClientType;
import pet.project.licensingservice.external.client.OrganizationClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoadBalancedRestTemplateOrganizationClient implements OrganizationClient {

    private final RestTemplate loadBalancedRestTemplate;

    @Override
    @CircuitBreaker(name = "organizationClient", fallbackMethod = "buildFallbackLicenseList")
    @Bulkhead(name = "bulkheadOrganizationClient", fallbackMethod = "buildFallbackLicenseList")
    @Retry(name = "retryOrganizationClient", fallbackMethod = "buildFallbackLicenseList")
    @RateLimiter(name = "organizationClient", fallbackMethod = "buildFallbackLicenseList")
    public Optional<Organization> getOrganization(String organizationId) {
        ResponseEntity<Organization> restExchange =
                loadBalancedRestTemplate.exchange(
                        "http://organization-service/v1/organization/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, organizationId);

        return Optional.ofNullable(restExchange.getBody());
    }

    @Override
    public ClientType getClientType() {
        return ClientType.REST;
    }
}

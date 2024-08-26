package pet.project.licensingservice.external.client.impl;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pet.project.licensingservice.external.api.model.Organization;
import pet.project.licensingservice.external.client.ClientType;
import pet.project.licensingservice.external.client.FeignOrganizationClient;
import pet.project.licensingservice.external.client.OrganizationClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FeignOrganizationClientAdapter implements OrganizationClient {

    private final FeignOrganizationClient feignOrganizationClient;

    @Override
    @CircuitBreaker(name = "organizationClient", fallbackMethod = "buildFallbackLicenseList")
    @Bulkhead(name = "bulkheadOrganizationClient", fallbackMethod = "buildFallbackLicenseList")
    @Retry(name = "retryOrganizationClient", fallbackMethod = "buildFallbackLicenseList")
    @RateLimiter(name = "organizationClient", fallbackMethod = "buildFallbackLicenseList")
    public Optional<Organization> getOrganization(String organizationId) {
        return Optional.ofNullable(feignOrganizationClient.getOrganization(organizationId));
    }

    @Override
    public ClientType getClientType() {
        return ClientType.FEIGN;
    }
}

package pet.project.licensingservice.external.client.impl;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pet.project.licensingservice.external.api.model.Organization;
import pet.project.licensingservice.external.client.ClientType;
import pet.project.licensingservice.external.client.OrganizationClient;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DiscoveryOrganizationClient implements OrganizationClient {

    private DiscoveryClient discoveryClient;

    @Override
    @CircuitBreaker(name = "organizationClient", fallbackMethod = "buildFallbackLicenseList")
    @Bulkhead(name = "bulkheadOrganizationClient", fallbackMethod = "buildFallbackLicenseList")
    @Retry(name = "retryOrganizationClient", fallbackMethod = "buildFallbackLicenseList")
    @RateLimiter(name = "organizationClient", fallbackMethod = "buildFallbackLicenseList")
    public Optional<Organization> getOrganization(String organizationId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("organization-service");

        if (instances.isEmpty()) {
            return Optional.empty();
        }
        String serviceUri = String.format("%s/v1/organization/%s",
                instances.getFirst().getUri().toString(),
                organizationId
        );

        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, Organization.class, organizationId);

        return Optional.ofNullable(restExchange.getBody());
    }

    @Override
    public ClientType getClientType() {
        return ClientType.DISCOVERY;
    }
}

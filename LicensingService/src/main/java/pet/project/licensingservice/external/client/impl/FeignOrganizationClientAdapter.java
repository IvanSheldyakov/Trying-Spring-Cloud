package pet.project.licensingservice.external.client.impl;

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
    public Optional<Organization> getOrganization(String organizationId) {
        return Optional.ofNullable(feignOrganizationClient.getOrganization(organizationId));
    }

    @Override
    public ClientType getClientType() {
        return ClientType.FEIGN;
    }
}

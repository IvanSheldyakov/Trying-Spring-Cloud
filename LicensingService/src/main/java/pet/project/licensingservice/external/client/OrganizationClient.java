package pet.project.licensingservice.external.client;

import pet.project.licensingservice.external.api.model.Organization;

import java.util.Optional;

public interface OrganizationClient {

    Optional<Organization> getOrganization(String organizationId);

    ClientType getClientType();
}

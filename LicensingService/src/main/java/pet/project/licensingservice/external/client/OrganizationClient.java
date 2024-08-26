package pet.project.licensingservice.external.client;

import pet.project.licensingservice.entity.License;
import pet.project.licensingservice.external.api.model.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface OrganizationClient {

    Optional<Organization> getOrganization(String organizationId);

    ClientType getClientType();

    default List<License> buildFallbackLicenseList(String organizationId, Throwable t) {
        List<License> fallbackList = new ArrayList<>();
        License license = new License();
        license.setLicenseId("0000000-00-00000");
        license.setOrganizationId(organizationId);
        license.setProductName(
                "Sorry no licensing information currently available");
        fallbackList.add(license);
        return fallbackList;
    }
}

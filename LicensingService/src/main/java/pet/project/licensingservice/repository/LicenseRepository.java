package pet.project.licensingservice.repository;


import pet.project.licensingservice.entity.License;

import java.util.List;

public interface LicenseRepository {

    List<License> findByOrganizationId(String organizationId);

    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);

    void save(License license);

    void deleteById(String id);
}

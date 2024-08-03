package pet.project.licensingservice.repository;


import pet.project.licensingservice.entity.License;
import pet.project.licensingservice.model.LicenseDto;

import java.util.List;

public interface LicenseRepository {

    List<License> findByOrganizationId(String organizationId);

    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);

    int save(License license);

    int deleteById(String id);

}

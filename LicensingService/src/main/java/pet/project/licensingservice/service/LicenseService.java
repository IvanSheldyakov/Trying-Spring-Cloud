package pet.project.licensingservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import pet.project.licensingservice.external.client.ClientType;
import pet.project.licensingservice.external.client.OrganizationClientProvider;
import pet.project.licensingservice.model.License;
import pet.project.licensingservice.repository.LicenseRepository;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messageSource;
    private final LicenseRepository licenseRepository;
    private final OrganizationClientProvider clientProvider;

    public License getLicense(String licenseId, String organizationId, Locale locale, ClientType clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (license == null) {
            throw new IllegalArgumentException(
                    String.format(messageSource.getMessage(
                            "license.search.error.message", null, locale)));
        }
        return license;
    }

    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license;
    }

    public License updateLicense(License license) {
        licenseRepository.save(license);
        return license;
    }

    public String deleteLicense(String licenseId, Locale locale) {

        licenseRepository.deleteById(licenseId);

        return String.format(messageSource.getMessage(
                "license.delete.message", null, locale), licenseId);
    }
}

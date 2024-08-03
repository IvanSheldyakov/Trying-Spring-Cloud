package pet.project.licensingservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import pet.project.licensingservice.entity.License;
import pet.project.licensingservice.external.api.model.Organization;
import pet.project.licensingservice.external.client.ClientType;
import pet.project.licensingservice.external.client.OrganizationClient;
import pet.project.licensingservice.external.client.OrganizationClientProvider;
import pet.project.licensingservice.mapper.LicenseMapper;
import pet.project.licensingservice.model.LicenseDto;
import pet.project.licensingservice.repository.LicenseRepository;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messageSource;

    private final LicenseRepository licenseRepository;

    private final OrganizationClientProvider clientProvider;

    private final LicenseMapper licenseMapper;

    public LicenseDto getLicense(String licenseId, String organizationId, Locale locale, ClientType clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (license == null) {
            throw new IllegalArgumentException(
                    String.format(messageSource.getMessage(
                            "license.search.error.message", null, locale)));
        }

        OrganizationClient client = clientProvider.getByClientType(clientType);
        Organization organization = client.getOrganization(organizationId).orElseThrow();

        return licenseMapper.map(license, organization);
    }

    public void createLicense(LicenseDto licenseDto) {
        License license = licenseMapper.map(licenseDto)
                .toBuilder()
                .licenseId(UUID.randomUUID().toString())
                .build();

        licenseRepository.save(license);
    }

    public LicenseDto updateLicense(LicenseDto licenseDto) {
        licenseRepository.save(licenseMapper.map(licenseDto));
        return licenseDto;
    }

    public String deleteLicense(String licenseId, Locale locale) {

        licenseRepository.deleteById(licenseId);

        return String.format(messageSource.getMessage(
                "license.delete.message", null, locale), licenseId);
    }
}

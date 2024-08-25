package pet.project.licensingservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import pet.project.licensingservice.context.ExecutionContext;
import pet.project.licensingservice.context.ExecutionContextHolder;
import pet.project.licensingservice.entity.License;
import pet.project.licensingservice.external.api.model.Organization;
import pet.project.licensingservice.external.client.ClientType;
import pet.project.licensingservice.external.client.OrganizationClient;
import pet.project.licensingservice.external.client.OrganizationClientProvider;
import pet.project.licensingservice.mapper.LicenseMapper;
import pet.project.licensingservice.model.LicenseDto;
import pet.project.licensingservice.model.LicenseIdDto;
import pet.project.licensingservice.repository.LicenseRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final MessageSource messageSource;

    private final LicenseRepository licenseRepository;

    private final OrganizationClientProvider clientProvider;

    private final LicenseMapper licenseMapper;

    public LicenseDto getLicense(String licenseId, String organizationId, ClientType clientType) {
        ExecutionContext context = ExecutionContextHolder.getContext();
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (license == null) {
            throw new IllegalArgumentException(getMessage(context));
        }

        OrganizationClient client = clientProvider.getByClientType(clientType);
        Organization organization = client.getOrganization(organizationId).orElseThrow();

        return licenseMapper.map(license, organization);
    }

    private String getMessage(ExecutionContext context) {
        return String.format(
                messageSource.getMessage(
                        "license.search.error.message",
                        null,
                        context.getLocale()
                )
        );
    }

    public LicenseIdDto createLicense(LicenseDto licenseDto) {
        License license = licenseMapper.map(licenseDto)
                .toBuilder()
                .licenseId(UUID.randomUUID().toString())
                .build();

        licenseRepository.save(license);
        return new LicenseIdDto(license.getLicenseId());
    }

    public void updateLicense(LicenseDto licenseDto) {
        licenseRepository.save(licenseMapper.map(licenseDto));
    }

    public String deleteLicense(String licenseId) {
        ExecutionContext context = ExecutionContextHolder.getContext();

        licenseRepository.deleteById(licenseId);

        return String.format(
                messageSource.getMessage("license.delete.message", null, context.getLocale()),
                licenseId
        );
    }
}

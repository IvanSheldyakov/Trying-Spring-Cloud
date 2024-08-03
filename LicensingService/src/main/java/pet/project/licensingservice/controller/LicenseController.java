package pet.project.licensingservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.project.licensingservice.external.client.ClientType;
import pet.project.licensingservice.model.LicenseDto;
import pet.project.licensingservice.service.LicenseService;

import java.util.Locale;

@RestController
@RequestMapping("v1/license")
public class LicenseController {

    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/{licenseId}/organization/{organizationId}")
    public ResponseEntity<LicenseDto> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId,
            @RequestParam("clientType") ClientType clientType,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {

        LicenseDto licenseDto = licenseService.getLicense(licenseId, organizationId, locale, clientType);
        return ResponseEntity.ok(licenseDto);
    }

    @PutMapping
    public ResponseEntity<LicenseDto> updateLicense(
            @RequestBody LicenseDto licenseDto
    ) {
        return ResponseEntity.ok(licenseService.updateLicense(licenseDto));
    }

    @PostMapping
    public void createLicense(
            @RequestBody LicenseDto licenseDto
    ) {
        licenseService.createLicense(licenseDto);
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("licenseId") String licenseId,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, locale));
    }
}

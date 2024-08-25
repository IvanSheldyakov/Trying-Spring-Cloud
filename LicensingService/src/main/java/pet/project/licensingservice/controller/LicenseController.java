package pet.project.licensingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.project.licensingservice.external.client.ClientType;
import pet.project.licensingservice.model.LicenseDto;
import pet.project.licensingservice.model.LicenseIdDto;
import pet.project.licensingservice.service.LicenseService;

@RestController
@RequestMapping("v1/license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping("/{licenseId}/organization/{organizationId}")
    public LicenseDto getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId,
            @RequestParam("clientType") ClientType clientType
    ) {
        return licenseService.getLicense(licenseId, organizationId, clientType);
    }

    @PutMapping
    public void updateLicense(
            @RequestBody LicenseDto licenseDto
    ) {
        licenseService.updateLicense(licenseDto);
    }

    @PostMapping
    public LicenseIdDto createLicense(
            @RequestBody LicenseDto licenseDto
    ) {
        return licenseService.createLicense(licenseDto);

    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("licenseId") String licenseId
    ) {
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId));
    }
}

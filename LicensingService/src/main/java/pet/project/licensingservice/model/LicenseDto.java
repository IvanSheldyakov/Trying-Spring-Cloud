package pet.project.licensingservice.model;

public record LicenseDto(

        String licenseId,

        String description,

        String organizationId,

        String productName,

        String licenseType,

        String comment,

        String organizationName,

        String contactName,

        String contactPhone,

        String contactEmail
) {
}

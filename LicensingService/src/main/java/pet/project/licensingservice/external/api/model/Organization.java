package pet.project.licensingservice.external.api.model;

public record Organization(
        String id,

        String name,

        String contactName,

        String contactEmail,

        String contactPhone
) {
}

package pet.project.licensingservice.entity;

import lombok.Builder;

@Builder(toBuilder = true)
public record License(

        String licenseId,

        String description,

        String organizationId,

        String productName,

        String licenseType,

        String comment
) {
}

package pet.project.licensingservice.entity;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Setter
public class License {

    private String licenseId;

    private String description;

    private String organizationId;

    private String productName;

    private String licenseType;

    private String comment;
}

package pet.project.licensingservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
public class License extends RepresentationModel<License> {

    private String licenseId;

    private String description;

    private String organizationId;

    private String productName;

    private String licenseType;

    private String comment;
}

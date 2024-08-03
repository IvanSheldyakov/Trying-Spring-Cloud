package pet.project.licensingservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet.project.licensingservice.entity.License;
import pet.project.licensingservice.external.api.model.Organization;
import pet.project.licensingservice.model.LicenseDto;

@Mapper(componentModel = "spring")
public interface LicenseMapper {

    @Mapping(target = "organizationName", ignore = true)
    @Mapping(target = "contactName", ignore = true)
    @Mapping(target = "contactEmail", ignore = true)
    @Mapping(target = "contactPhone", ignore = true)
    LicenseDto map(License license);

    License map(LicenseDto licenseDto);

    LicenseDto map(License license, Organization organization);
}

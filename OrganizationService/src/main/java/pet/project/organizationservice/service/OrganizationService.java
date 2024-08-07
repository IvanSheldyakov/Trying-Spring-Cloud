package pet.project.organizationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.project.organizationservice.model.Organization;
import pet.project.organizationservice.repository.OrganizationRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository repository;

    public Organization findById(String organizationId) {
        Optional<Organization> opt = repository.findById(organizationId);
        return opt.orElse(null);
    }

    public Organization create(Organization organization) {
        organization.setId(UUID.randomUUID().toString());
        organization = repository.save(organization);
        return organization;

    }

    public void update(Organization organization) {
        repository.save(organization);
    }

    public void delete(Organization organization) {
        repository.deleteById(organization.getId());
    }
}
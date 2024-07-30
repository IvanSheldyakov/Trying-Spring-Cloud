package pet.project.organizationservice.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pet.project.organizationservice.model.Organization;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String> {

    Optional<Organization> findById(String organizationId);
}
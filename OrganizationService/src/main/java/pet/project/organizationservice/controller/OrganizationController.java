package pet.project.organizationservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pet.project.organizationservice.model.Organization;
import pet.project.organizationservice.service.OrganizationService;

@RestController
@RequestMapping(value = "v1/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService service;

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        return service.findById(organizationId);
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.PUT)
    public void updateOrganization(@PathVariable("organizationId") String id, @RequestBody Organization organization) {
        service.update(organization);
    }

    @PostMapping
    public Organization saveOrganization(@RequestBody Organization organization) {
        return service.create(organization);
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@RequestBody Organization organization, @PathVariable String organizationId) {
        service.delete(organization);
    }

}

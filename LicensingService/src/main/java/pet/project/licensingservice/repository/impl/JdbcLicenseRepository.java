package pet.project.licensingservice.repository.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pet.project.licensingservice.entity.License;
import pet.project.licensingservice.repository.LicenseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@Transactional
@RequiredArgsConstructor
public class JdbcLicenseRepository implements LicenseRepository {

    private static final String SELECT_BY_ORG_ID =
            "select * from licenses where organization_id = :organizationId";

    private static final String SELECT_BY_ORG_ID_AND_LICENSE_ID =
            "select * from licenses where license_id = :licenseId and organization_id = :organizationId";

    private static final String INSERT_LICENSE =
            "insert into licenses(license_id, description, organization_id, product_name, license_type, comment)" +
                    " values (:licenseId, :description, :organizationId, :productName, :licenseType, :comment)";

    private static final String DELETE_BY_ID =
            "delete from licenses where license_id = :licenseId";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<License> findByOrganizationId(String organizationId) {
        Map<String, Object> params = new HashMap<>();
        params.put("organizationId", organizationId);
        return namedParameterJdbcTemplate.query(
                SELECT_BY_ORG_ID,
                params,
                BeanPropertyRowMapper.newInstance(License.class)
        );
    }

    @Override
    public License findByOrganizationIdAndLicenseId(String organizationId, String licenseId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("organizationId", organizationId);
            params.put("licenseId", licenseId);
            return namedParameterJdbcTemplate.queryForObject(
                    SELECT_BY_ORG_ID_AND_LICENSE_ID,
                    params,
                    BeanPropertyRowMapper.newInstance(License.class)
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            // Add logging here
            return null;
        }
    }

    @Override
    public void save(License license) {
        Map<String, Object> params = new HashMap<>();
        params.put("licenseId", license.getLicenseId());
        params.put("description", license.getLicenseId());
        params.put("organizationId", license.getOrganizationId());
        params.put("productName", license.getProductName());
        params.put("licenseType", license.getLicenseType());
        params.put("comment", license.getComment());
        namedParameterJdbcTemplate.update(INSERT_LICENSE, params);
    }

    @Override
    public void deleteById(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("licenseId", id);
        namedParameterJdbcTemplate.update(DELETE_BY_ID, params);
    }
}

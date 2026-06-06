package com.tys.legalbases.domain.model.repository;

import com.tys.legalbases.domain.model.entity.NationalNormEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class NationalNormSpecification {

    private NationalNormSpecification() {}

    /**
     * Returns a Specification that matches the search term against all text fields.
     * When search is null or blank, no filter is applied (returns all records).
     */
    public static Specification<NationalNormEntity> searchAllFields(String search) {
        return (root, query, cb) -> {
            if (search == null || search.isBlank()) {
                return cb.conjunction(); // no filter
            }
            String pattern = "%" + search.trim().toLowerCase() + "%";

            Predicate titleMatch       = cb.like(cb.lower(root.get("title").as(String.class)),       pattern);
            Predicate descriptionMatch = cb.like(cb.lower(root.get("description").as(String.class)), pattern);
            Predicate typeMatch        = cb.like(cb.lower(root.get("type").as(String.class)),        pattern);
            Predicate sourceUrlMatch   = cb.like(cb.lower(root.get("sourceUrl").as(String.class)),   pattern);

            return cb.or(titleMatch, descriptionMatch, typeMatch, sourceUrlMatch);
        };
    }
}

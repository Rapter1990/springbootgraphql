package com.springboot.graphql.repository;

import com.springboot.graphql.model.Hospital;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface HospitalRepository extends CrudRepository<Hospital, Integer>,
        JpaSpecificationExecutor<Hospital> {

}

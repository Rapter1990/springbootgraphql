package com.springboot.graphql.repository;

import com.springboot.graphql.model.Doctor;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Integer>,
        JpaSpecificationExecutor<Doctor> {

}

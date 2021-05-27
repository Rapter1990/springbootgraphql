package com.springboot.graphql.resolver;

import com.springboot.graphql.model.Hospital;
import com.springboot.graphql.repository.HospitalRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HospitalQueryResolver implements GraphQLQueryResolver {

    HospitalRepository hospitalRepository;

    @Autowired
    public HospitalQueryResolver(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Iterable<Hospital> hospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital hospital(Integer id) {
        return hospitalRepository.findById(id).orElseThrow();
    }

}

package com.springboot.graphql.resolver;

import com.springboot.graphql.model.Hospital;
import com.springboot.graphql.repository.HospitalRepository;
import com.springboot.graphql.request.HospitalInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HospitalMutableResolver implements GraphQLMutationResolver {

    HospitalRepository hospitalRepository;

    @Autowired
    public HospitalMutableResolver(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital newHospital(HospitalInput hospitalInput) {
        return hospitalRepository.save(new Hospital(null, hospitalInput.getName(), null, null));
    }

    public Hospital updateHospital(Integer id, String name){
        Hospital updateHospital = new Hospital();
        updateHospital.setId(id);
        updateHospital.setName(name);

        return hospitalRepository.save(updateHospital);
    }

    public boolean deleteHospital(Integer id){
        hospitalRepository.deleteById(id);
        return true;
    }

}

package com.springboot.graphql.resolver;

import com.springboot.graphql.model.Department;
import com.springboot.graphql.model.Hospital;
import com.springboot.graphql.repository.DepartmentRepository;
import com.springboot.graphql.repository.HospitalRepository;
import com.springboot.graphql.request.DepartmentInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMutableResolver implements GraphQLMutationResolver {

    DepartmentRepository departmentRepository;

    HospitalRepository hospitalRepository;

    @Autowired
    public DepartmentMutableResolver(DepartmentRepository departmentRepository, HospitalRepository hospitalRepository) {
        this.departmentRepository = departmentRepository;
        this.hospitalRepository = hospitalRepository;
    }

    public Department newDepartment(DepartmentInput departmentInput) {
        Hospital hospital = hospitalRepository.findById(departmentInput.getHospitalId()).get();
        return departmentRepository.save(new Department(null, departmentInput.getName(), null, hospital));
    }

    public Department updateDepartment(Integer id , DepartmentInput departmentInput) {
        Hospital hospital = hospitalRepository.findById(departmentInput.getHospitalId()).get();

        Department updatedDepartment = new Department();
        updatedDepartment.setId(id);
        updatedDepartment.setHospital(hospital);
        updatedDepartment.setName(departmentInput.getName());
        updatedDepartment.setDoctors(null);

        return departmentRepository.save(updatedDepartment);
    }

    public boolean deleteDepartment(Integer id){
        departmentRepository.deleteById(id);
        return true;
    }

}

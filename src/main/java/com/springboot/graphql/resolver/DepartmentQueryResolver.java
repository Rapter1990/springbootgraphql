package com.springboot.graphql.resolver;

import com.springboot.graphql.model.Department;
import com.springboot.graphql.model.Doctor;
import com.springboot.graphql.model.Hospital;
import com.springboot.graphql.repository.DepartmentRepository;
import com.springboot.graphql.repository.HospitalRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class DepartmentQueryResolver implements GraphQLQueryResolver {

    DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentQueryResolver(DepartmentRepository departmentRepository, HospitalRepository hospitalRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Iterable<Department> departments(DataFetchingEnvironment environment) {
        DataFetchingFieldSelectionSet s = environment.getSelectionSet();
        List<Specification<Department>> specifications = new ArrayList<>();
        if (s.contains("doctors") && !s.contains("hospital"))
            return departmentRepository.findAll(fetchDoctors());
        else if (!s.contains("doctors") && s.contains("hospital"))
            return departmentRepository.findAll(fetchHospital());
        else if (s.contains("doctors") && s.contains("hospital"))
            return departmentRepository.findAll(fetchDoctors().and(fetchHospital()));
        else
            return departmentRepository.findAll();
    }

    public Department department(Integer id, DataFetchingEnvironment environment) {
        Specification<Department> spec = byId(id);
        DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();
        if (selectionSet.contains("doctors"))
            spec = spec.and(fetchDoctors());
        if (selectionSet.contains("hospital"))
            spec = spec.and(fetchHospital());
        return departmentRepository.findOne(spec).orElseThrow(NoSuchElementException::new);
    }

    private Specification<Department> fetchHospital() {
        return (Specification<Department>) (root, query, builder) -> {
            Fetch<Department, Hospital> f = root.fetch("hospital", JoinType.LEFT);
            Join<Department, Hospital> join = (Join<Department, Hospital>) f;
            return join.getOn();
        };
    }

    private Specification<Department> fetchDoctors() {
        return (Specification<Department>) (root, query, builder) -> {
            Fetch<Department, Doctor> f = root.fetch("doctors", JoinType.LEFT);
            Join<Department, Doctor> join = (Join<Department, Doctor>) f;
            return join.getOn();
        };
    }

    private Specification<Department> byId(Integer id) {
        return (Specification<Department>) (root, query, builder) -> builder.equal(root.get("id"), id);
    }

}

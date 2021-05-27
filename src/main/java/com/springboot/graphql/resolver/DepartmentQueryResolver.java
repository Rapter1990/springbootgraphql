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
            return departmentRepository.findAll(fetchEmployees());
        else if (!s.contains("doctors") && s.contains("hospital"))
            return departmentRepository.findAll(fetchOrganization());
        else if (s.contains("doctors") && s.contains("hospital"))
            return departmentRepository.findAll(fetchEmployees().and(fetchOrganization()));
        else
            return departmentRepository.findAll();
    }

    public Department department(Integer id, DataFetchingEnvironment environment) {
        Specification<Department> spec = byId(id);
        DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();
        if (selectionSet.contains("employees"))
            spec = spec.and(fetchEmployees());
        if (selectionSet.contains("organization"))
            spec = spec.and(fetchOrganization());
        return departmentRepository.findOne(spec).orElseThrow(NoSuchElementException::new);
    }

    private Specification<Department> fetchOrganization() {
        return (Specification<Department>) (root, query, builder) -> {
            Fetch<Department, Hospital> f = root.fetch("organization", JoinType.LEFT);
            Join<Department, Hospital> join = (Join<Department, Hospital>) f;
            return join.getOn();
        };
    }

    private Specification<Department> fetchEmployees() {
        return (Specification<Department>) (root, query, builder) -> {
            Fetch<Department, Doctor> f = root.fetch("employees", JoinType.LEFT);
            Join<Department, Doctor> join = (Join<Department, Doctor>) f;
            return join.getOn();
        };
    }

    private Specification<Department> byId(Integer id) {
        return (Specification<Department>) (root, query, builder) -> builder.equal(root.get("id"), id);
    }
}

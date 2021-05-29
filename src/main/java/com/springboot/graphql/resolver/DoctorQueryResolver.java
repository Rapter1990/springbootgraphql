package com.springboot.graphql.resolver;

import com.springboot.graphql.filter.DoctorFilter;
import com.springboot.graphql.filter.FilterField;
import com.springboot.graphql.model.Department;
import com.springboot.graphql.model.Doctor;
import com.springboot.graphql.model.Hospital;
import com.springboot.graphql.repository.DoctorRepository;
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
public class DoctorQueryResolver implements GraphQLQueryResolver {

    DoctorRepository doctorRepository;

    @Autowired
    public DoctorQueryResolver(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Iterable<Doctor> doctors(DataFetchingEnvironment environment) {

        DataFetchingFieldSelectionSet s = environment.getSelectionSet();
        List<Specification<Department>> specifications = new ArrayList<>();
        if (s.contains("department") && !s.contains("hospital"))
            return doctorRepository.findAll(fetchDepartment());
        else if (!s.contains("department") && s.contains("hospital"))
            return doctorRepository.findAll(fetchHospital());
        else if (s.contains("department") && s.contains("hospital"))
            return doctorRepository.findAll(fetchDepartment().and(fetchHospital()));
        else
            return doctorRepository.findAll();
    }

    public Doctor doctor(Integer id, DataFetchingEnvironment environment) {

        Specification<Doctor> spec = byId(id);
        DataFetchingFieldSelectionSet selectionSet = environment.getSelectionSet();
        if (selectionSet.contains("department"))
            spec = spec.and(fetchDepartment());
        if (selectionSet.contains("hospital"))
            spec = spec.and(fetchHospital());
        return doctorRepository.findOne(spec).orElseThrow(NoSuchElementException::new);

    }


    public Iterable<Doctor> doctorsWithFilter(DoctorFilter filter) {
        Specification<Doctor> spec = null;
        if (filter.getSalary() != null)
            spec = bySalary(filter.getSalary());
        if (filter.getBirthday() != null)
            spec = (spec == null ? byBirthday(filter.getBirthday()) : spec.and(byBirthday(filter.getBirthday())));
        if (filter.getAge() != null)
            spec = (spec == null ? byAge(filter.getAge()) : spec.and(byAge(filter.getAge())));
        if (filter.getPosition() != null)
            spec = (spec == null ? byPosition(filter.getPosition()) :
                    spec.and(byPosition(filter.getPosition())));
        if (spec != null)
            return doctorRepository.findAll(spec);
        else
            return doctorRepository.findAll();
    }

    private Specification<Doctor> fetchHospital() {
        return (Specification<Doctor>) (root, query, builder) -> {
            Fetch<Doctor, Hospital> f = root.fetch("hospital", JoinType.LEFT);
            Join<Doctor, Hospital> join = (Join<Doctor, Hospital>) f;
            return join.getOn();
        };
    }

    private Specification<Doctor> fetchDepartment() {
        return (Specification<Doctor>) (root, query, builder) -> {
            Fetch<Doctor, Department> f = root.fetch("department", JoinType.LEFT);
            Join<Doctor, Department> join = (Join<Doctor, Department>) f;
            return join.getOn();
        };
    }

    private Specification<Doctor> byId(Integer id) {
        return (Specification<Doctor>) (root, query, builder) -> builder.equal(root.get("id"), id);
    }

    private Specification<Doctor> bySalary(FilterField filterField) {
        return (Specification<Doctor>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("salary"));
    }

    private Specification<Doctor> byAge(FilterField filterField) {
        return (Specification<Doctor>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("age"));
    }

    private Specification<Doctor> byPosition(FilterField filterField) {
        return (Specification<Doctor>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("position"));
    }

    private Specification<Doctor> byBirthday(FilterField filterField) {
        return (Specification<Doctor>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("birthday"));
    }

}

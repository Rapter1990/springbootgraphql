package com.springboot.graphql.resolver;

import com.springboot.graphql.filter.DoctorFilter;
import com.springboot.graphql.filter.FilterField;
import com.springboot.graphql.model.Doctor;
import com.springboot.graphql.repository.DoctorRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DoctorQueryResolver implements GraphQLQueryResolver {

    DoctorRepository doctorRepository;

    @Autowired
    public DoctorQueryResolver(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Iterable<Doctor> doctors() {
        return doctorRepository.findAll();
    }

    public Doctor doctor(Integer id) {
        return doctorRepository.findById(id).get();
    }

    public Iterable<Doctor> doctorsWithFilter(DoctorFilter filter) {
        Specification<Doctor> spec = null;
        if (filter.getSalary() != null)
            spec = bySalary(filter.getSalary());
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

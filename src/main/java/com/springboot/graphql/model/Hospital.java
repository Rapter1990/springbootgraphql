package com.springboot.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hospital implements Serializable {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "hospital")
    private Set<Department> departments;

    @OneToMany(mappedBy = "hospital")
    private Set<Doctor> doctors;
}

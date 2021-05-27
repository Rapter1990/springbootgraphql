package com.springboot.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Doctor implements Serializable {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer id;

    private String firstName;

    private String lastName;

    private String position;

    private int salary;

    private int age;

    private Date birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hospital hospital;
}

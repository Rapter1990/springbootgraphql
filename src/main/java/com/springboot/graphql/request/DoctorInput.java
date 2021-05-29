package com.springboot.graphql.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorInput {

    private String firstName;
    private String lastName;
    private String position;
    private int salary;
    private int age;
    private LocalDate birthday;
    private Integer departmentId;
    private Integer hospitalId;

}

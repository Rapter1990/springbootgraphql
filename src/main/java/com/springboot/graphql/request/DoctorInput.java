package com.springboot.graphql.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorInput {
    private String firstName;
    private String lastName;
    private String position;
    private int salary;
    private int age;
    private Date birthday;
    private Integer departmentId;
    private Integer organizationId;
}

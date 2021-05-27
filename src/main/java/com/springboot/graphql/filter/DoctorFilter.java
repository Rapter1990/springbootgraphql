package com.springboot.graphql.filter;

import lombok.Data;

@Data
public class DoctorFilter {

    private FilterField salary;
    private FilterField age;
    private FilterField position;
    private FilterField birthday;

}

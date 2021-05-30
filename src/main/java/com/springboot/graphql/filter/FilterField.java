package com.springboot.graphql.filter;

import lombok.Data;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class FilterField {

    private String operator;
    private String value;

    public Predicate generateCriteria(CriteriaBuilder builder, Path field) {
        try {
            int v = Integer.parseInt(value);
            switch (operator) {
                case "lt": return builder.lt(field, v);
                case "le": return builder.le(field, v);
                case "gt": return builder.gt(field, v);
                case "ge": return builder.ge(field, v);
                case "eq": return builder.equal(field, v);
            }
        } catch (NumberFormatException e) {
            switch (operator) {
                case "endsWith": return builder.like(field, "%" + value);
                case "startsWith": return builder.like(field, value + "%");
                case "contains": return builder.like(field, "%" + value + "%");
                case "eq": return builder.equal(field, value);
                case "birthdayDate":

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    String[] values = value.split(",");

                    // "1980-01-01"
                    // "1990-05-01"

                    LocalDate dBegin = LocalDate.parse(values[0], formatter);
                    LocalDate dEnd = LocalDate.parse(values[1], formatter);

                    System.out.println("dBegin : " + dBegin);
                    System.out.println("dEnd : " + dEnd);

                    return builder.between(field,
                                dBegin,
                                dEnd);
            }
        }

        return null;
    }
}

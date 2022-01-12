package com.demo.jdbccrud.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

}

package com.example.springbootdemo.student.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Student {
    private String name;
    private String email;
    private int age;
}

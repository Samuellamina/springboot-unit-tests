package com.example.springbootdemo.student.builder;

public class TestUser {
    public static void main(String[] args) {

        Student s1 = Student.builder()
                .name("Sam")
                .email("sasas@sss.com")
                .age(12)
                .build();

        Student s2 = Student.builder()
                .build();

        Student s3 = Student.builder()
                .name("X")
                .build();

        System.out.println(s1 + " " + s2 + " " + s3);


    }
}

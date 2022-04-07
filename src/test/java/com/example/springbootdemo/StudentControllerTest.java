package com.example.springbootdemo;

import com.example.springbootdemo.student.Student;
import com.example.springbootdemo.student.StudentController;
import com.example.springbootdemo.student.StudentRepository;
import com.example.springbootdemo.student.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.time.Month.AUGUST;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StudentRepository studentRepository;

    @MockBean
    StudentService studentService;

    Student R1 = new Student(
            1l,
            "RECORD ONE",
            "TWO@gmail.com",
            LocalDate.of(2004, AUGUST, 5)
    );

    Student R2 = new Student(
            2l,
            "RECORD TWO",
            "TWO@gmail.com",
            LocalDate.of(2000, AUGUST, 5)
    );

    // test case
    @Test // this is for junit to pick it up
    public void getStudents_success() throws Exception {
        List<Student> students = new ArrayList<>(Arrays.asList(R1, R2));

        Mockito.when(studentService.getStudents()).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/student")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("RECORD TWO")));
    }

//    @Test
//    public void registerNewStudent_success() throws Exception {
//        Student student = new Student(
//                "RECORD THREE",
//                "THREE@gmail.com",
//                LocalDate.of(2000, AUGUST, 5)
//        );
//
//        Mockito.when(studentRepository.save(student)).thenReturn(student);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/student")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(student));
//
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("RECORD THREE")));
//    }
//
//    @Test
//    public void updateStudent_success () throws Exception {
//        Student updatedStudent = new Student(
//                2l,
//                "ss",
//                "THREE@gmail.com",
//                LocalDate.of(2000, AUGUST, 5)
//        );
//
//        Mockito.when(studentRepository.findById(R2.getId())).thenReturn(Optional.of(R1));
//        Mockito.when(studentRepository.save(updatedStudent)).thenReturn(updatedStudent);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/student")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(updatedStudent));
//
//        mockMvc.perform(mockRequest)
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("ss")));
//    }
}

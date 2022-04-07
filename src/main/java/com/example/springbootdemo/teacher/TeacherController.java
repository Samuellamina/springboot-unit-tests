package com.example.springbootdemo.teacher;

import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    public List<Teacher> getAllRecords() {
        return teacherRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public Teacher getTeacherById(@PathVariable(value="id") Long id) {
        return teacherRepository.findById(id).get();
    }

    @PostMapping
    public Teacher createRecord(@RequestBody @Valid Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @PutMapping
    public Teacher updateTeacherRecord(@RequestBody Teacher teacher) throws NotFoundException {
        if (teacher == null || teacher.getId() == null) {
            throw new InvalidRequestStateException("PatientRecord or ID must not be null!");
        }
        Optional<Teacher> optionalRecord = teacherRepository.findById(teacher.getId());
        if (optionalRecord.isEmpty()) {
            throw new NotFoundException("Patient with ID " + teacher.getId() + " does not exist.");
        }
        Teacher existingTeacher = optionalRecord.get();

        existingTeacher.setName(teacher.getName());
        existingTeacher.setAge(teacher.getAge());
        existingTeacher.setAddress(teacher.getAddress());

        return teacherRepository.save(existingTeacher);
    }

    @DeleteMapping(value = "{id}")
    public void deleteTeacherById(@PathVariable(value = "id") Long Id) throws NotFoundException {
        if (teacherRepository.findById(Id).isEmpty()) {
            throw new NotFoundException("Patient with ID " + Id + " does not exist.");
        }
        teacherRepository.deleteById(Id);
    }
}

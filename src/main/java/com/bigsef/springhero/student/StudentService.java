package com.bigsef.springhero.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.studentRepository = repository;
    }

    public List<Student> allStudent() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> optionalStudent= studentRepository.findStudentByEmail(student.getEmail());

        if (optionalStudent.isPresent()) {
            throw new IllegalStateException("This email already exist.");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentID) {
        boolean isExist = studentRepository.existsById(studentID);

        if (!isExist) {
            throw new IllegalStateException("Student with id: " + studentID + "not exists any more");
        }

        studentRepository.deleteById(studentID);
    }

    @Transactional
    public void updateStudent(Long studentID, String name, String email) {
        Student student = studentRepository.findById(studentID).orElseThrow(() -> new IllegalStateException(("Student with id: " + studentID + "not exists any more")));

        // validate name and set it
        if (name != null && name.length() > 0  && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }

        // validate email and set it
        if (email != null && email.length() > 0 && !Objects.equals(email, student.getEmail()) ){
            Optional<Student> optionalStudent = studentRepository.findStudentByEmail(email);

            if (optionalStudent.isPresent()){
                throw new IllegalStateException("This email already exist.");
            }

            student.setEmail(email);
        }
    }
}

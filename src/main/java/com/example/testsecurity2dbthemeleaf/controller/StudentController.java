package com.example.testsecurity2dbthemeleaf.controller;

import com.example.testsecurity2dbthemeleaf.entity.Student;
import com.example.testsecurity2dbthemeleaf.repository.StudentRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Controller
public class StudentController {

  private final StudentRepository studentRepository;

  @GetMapping("/list")
  public ModelAndView getAllStudents() {
    log.info("/list -> connection");
    ModelAndView mav = new ModelAndView("list-students");
    mav.addObject("students", studentRepository.findAll());
    return mav;
  }

  @GetMapping("/addStudentForm")
  public ModelAndView addStudentForm() {
    ModelAndView mav = new ModelAndView("add-student-form");
    Student student = new Student();
    mav.addObject("student", student);
    return mav;
  }

  @PostMapping("/saveStudent")
  public String saveStudent(@ModelAttribute Student student) {
    studentRepository.save(student);
    return "redirect:/list";
  }

  @GetMapping("/showUpdateForm")
  public ModelAndView showUpdateForm(@RequestParam Long studentId) {
    ModelAndView mav = new ModelAndView("add-student-form");
    Optional<Student> optionalStudent = studentRepository.findById(studentId);
    Student student = new Student();
    if (optionalStudent.isPresent()) {
      student = optionalStudent.get();
    }
    mav.addObject("student", student);
    return mav;
  }

  @GetMapping("/deleteStudent")
  public String deleteStudent (@RequestParam Long studentId) {
    studentRepository.deleteById(studentId);
    return "redirect:/list";
  }
}

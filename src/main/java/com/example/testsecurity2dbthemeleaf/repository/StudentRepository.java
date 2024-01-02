package com.example.testsecurity2dbthemeleaf.repository;

import com.example.testsecurity2dbthemeleaf.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}

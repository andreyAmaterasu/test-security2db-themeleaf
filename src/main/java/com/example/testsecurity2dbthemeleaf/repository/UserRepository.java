package com.example.testsecurity2dbthemeleaf.repository;

import com.example.testsecurity2dbthemeleaf.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);
}

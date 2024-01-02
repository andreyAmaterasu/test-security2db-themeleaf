package com.example.testsecurity2dbthemeleaf.service;

import com.example.testsecurity2dbthemeleaf.dto.UserDto;
import com.example.testsecurity2dbthemeleaf.entity.Role;
import com.example.testsecurity2dbthemeleaf.entity.User;
import com.example.testsecurity2dbthemeleaf.repository.RoleRepository;
import com.example.testsecurity2dbthemeleaf.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public void saveUser(UserDto userDto) {

    User user = new User();
    user.setName(userDto.getFirstName() + " " + userDto.getLastName());
    user.setEmail(userDto.getEmail());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));

    Role role = roleRepository.findByName("ROLE_ADMIN");
    if (role == null) {
      role = checkRoleExist();
    }
    user.setRoles(Arrays.asList(role));
    userRepository.save(user);
  }
  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public List<UserDto> findAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
        .map(this::mapToUserDto)
        .collect(Collectors.toList());
  }

  private UserDto mapToUserDto (User user) {
    UserDto userDto = new UserDto();
    String[] str = user.getName().split(" ");
    userDto.setFirstName(str[0]);
    userDto.setLastName(str[1]);
    userDto.setEmail(user.getEmail());
    return userDto;
  }

  private Role checkRoleExist() {
    Role role = new Role();
    role.setName("ROLE_ADMIN");
    return roleRepository.save(role);
  }
}
package ru.hodzhakhov.users.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hodzhakhov.users.dao.entities.User;
import ru.hodzhakhov.users.dao.repositories.UserRepository;
import ru.hodzhakhov.users.services.dtos.UserDto;
import ru.hodzhakhov.users.services.mappers.UserMapper;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Transactional
  public void addUser(UserDto userDto) {
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    User user = UserMapper.userDtoToEntity(userDto);
    if (userRepository.findByUsername(user.getUsername()) != null) {
      return;
    }
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void addOwner(int userId, int ownerId) {
    User user = userRepository.findByUserID(userId);
    if (user.getOwner() != 0) {
      return;
    }
    user.setOwner(ownerId);
    userRepository.save(user);
  }
}

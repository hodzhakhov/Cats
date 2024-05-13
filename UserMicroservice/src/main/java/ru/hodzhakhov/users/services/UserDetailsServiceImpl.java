package ru.hodzhakhov.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hodzhakhov.users.dao.entities.User;
import ru.hodzhakhov.users.dao.repositories.UserRepository;
import ru.hodzhakhov.users.services.dtos.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getUserByUsername(username);
    return new UserDetailsImpl(user);
  }

  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}

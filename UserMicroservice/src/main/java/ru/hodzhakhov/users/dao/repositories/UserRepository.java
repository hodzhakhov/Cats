package ru.hodzhakhov.users.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hodzhakhov.users.dao.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByUsername(String username);

  User findByUserID(int UserID);
}

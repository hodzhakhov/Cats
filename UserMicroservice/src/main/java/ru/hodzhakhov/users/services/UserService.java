package ru.hodzhakhov.users.services;

import ru.hodzhakhov.users.services.dtos.UserDto;

public interface UserService {
  void addUser(UserDto userDto);

  void addOwner(int userId, int ownerId);
}

package ru.hodzhakhov.users.services.mappers;

import ru.hodzhakhov.users.dao.entities.User;
import ru.hodzhakhov.users.services.dtos.UserDto;

public class UserMapper {
  public static User userDtoToEntity(UserDto userDto) {
    return new User(userDto.getUsername(), userDto.getPassword(), userDto.getRoles());
  }
}

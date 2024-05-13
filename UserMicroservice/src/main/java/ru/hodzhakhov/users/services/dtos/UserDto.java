package ru.hodzhakhov.users.services.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hodzhakhov.users.dao.entities.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  private int UserID;

  private String username;

  private String password;

  private List<Role> roles;

  private int owner;

  public UserDto(String username, String password, List<Role> roles) {
    this.username = username;
    this.password = password;
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "User{"
        + "UserID="
        + UserID
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", roles="
        + roles
        + ", owner="
        + owner
        + '}';
  }
}

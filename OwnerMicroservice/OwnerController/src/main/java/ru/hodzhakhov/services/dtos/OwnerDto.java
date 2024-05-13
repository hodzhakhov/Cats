package ru.hodzhakhov.services.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
  private int OwnerID;
  private String Name;

  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate BirthDay;

  public OwnerDto(String name, LocalDate birthDay) {
    this.Name = name;
    this.BirthDay = birthDay;
  }

  @Override
  public String toString() {
    return "Owner: id = "
        + this.OwnerID
        + ", name = "
        + this.Name
        + ", birthday = "
        + this.BirthDay.toString();
  }
}

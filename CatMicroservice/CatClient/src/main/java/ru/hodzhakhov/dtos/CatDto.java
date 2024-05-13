package ru.hodzhakhov.dtos;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class CatDto {
  private int CatID;
  private String Name;

  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate BirthDay;

  private String Breed;
  private CatColor Color;
  private int Owner;

  public CatDto(String name, LocalDate birthDay, String breed, CatColor color) {
    this.Name = name;
    this.BirthDay = birthDay;
    this.Breed = breed;
    this.Color = color;
    this.Owner = -1;
  }

  public CatDto(
      int catID, String name, LocalDate birthDay, String breed, CatColor color, int owner) {
    this.CatID = catID;
    this.Name = name;
    this.BirthDay = birthDay;
    this.Breed = breed;
    this.Color = color;
    this.Owner = owner;
  }

  @Override
  public String toString() {
    return "Cat: id = "
        + this.CatID
        + ", name = "
        + this.Name
        + ", birthday = "
        + this.BirthDay.toString()
        + ", breed = "
        + this.Breed
        + ", color = "
        + this.Color;
  }
}

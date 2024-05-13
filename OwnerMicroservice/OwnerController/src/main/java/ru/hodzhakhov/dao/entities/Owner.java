package ru.hodzhakhov.dao.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Owner")
@Data
@NoArgsConstructor
public class Owner {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int OwnerID;

  private String Name;

  private LocalDate BirthDay;

  public Owner(String name, LocalDate birthDay) {
    this.Name = name;
    this.BirthDay = birthDay;
  }
}

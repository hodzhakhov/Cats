package ru.hodzhakhov.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOwnerDto {
  private int CatId;
  private int OwnerId;
}

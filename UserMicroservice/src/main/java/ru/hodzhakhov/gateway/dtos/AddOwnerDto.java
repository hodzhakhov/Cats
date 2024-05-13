package ru.hodzhakhov.gateway.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddOwnerDto {
  private int CatId;
  private int OwnerId;
}

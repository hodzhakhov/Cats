package ru.hodzhakhov.services.mappers;

import ru.hodzhakhov.dao.entities.Owner;
import ru.hodzhakhov.services.dtos.OwnerDto;

public class OwnerMapper {
  public static Owner ownerDtoToEntity(OwnerDto ownerDto) {
    return new Owner(ownerDto.getName(), ownerDto.getBirthDay());
  }

  public static OwnerDto ownerEntityToDto(Owner owner) {
    return new OwnerDto(owner.getOwnerID(), owner.getName(), owner.getBirthDay());
  }
}

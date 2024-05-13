package ru.hodzhakhov.gateway.services;

import java.util.List;
import ru.hodzhakhov.dtos.OwnerDto;

public interface OwnerService {
  List<OwnerDto> getAllOwners(String username);

  OwnerDto getOwnerById(String id, String username);

  void addOwner(OwnerDto ownerDto);

  void updateOwner(int id, OwnerDto ownerDto);

  void deleteOwner(int id);
}

package ru.hodzhakhov;

import java.util.List;
import ru.hodzhakhov.dtos.OwnerDto;

public interface OwnerClient {
  List<OwnerDto> getAllOwners();

  OwnerDto getOwnerById(String id);
}

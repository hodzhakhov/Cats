package ru.hodzhakhov.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hodzhakhov.services.OwnerService;
import ru.hodzhakhov.services.dtos.OwnerDto;

@RestController
@RequestMapping("api/owners")
public class OwnerController {
  private final OwnerService ownerService;

  @Autowired
  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @GetMapping("")
  public List<OwnerDto> getAllOwners() {
    return ownerService.getAllOwners();
  }

  @GetMapping("/{id}")
  public OwnerDto getOwnerById(@PathVariable("id") int id) {
    return ownerService.getOwnerById(id);
  }
}

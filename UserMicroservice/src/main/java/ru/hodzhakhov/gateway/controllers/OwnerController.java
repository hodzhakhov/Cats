package ru.hodzhakhov.gateway.controllers;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hodzhakhov.dtos.OwnerDto;
import ru.hodzhakhov.gateway.services.OwnerService;

@RestController
@RequestMapping("api/owners")
public class OwnerController {
  private final OwnerService ownerService;

  @Autowired
  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @GetMapping("")
  public List<OwnerDto> getAllOwners(Principal principal) {
    return ownerService.getAllOwners(principal.getName());
  }

  @GetMapping("/{id}")
  public OwnerDto getOwnerById(@PathVariable("id") String id, Principal principal) {
    return ownerService.getOwnerById(id, principal.getName());
  }

  @PostMapping("")
  public void addOwner(@RequestBody OwnerDto ownerDto) {
    ownerService.addOwner(ownerDto);
  }

  @PutMapping("/{id}")
  public void updateOwner(@PathVariable("id") int id, @RequestBody OwnerDto ownerDto) {
    ownerService.updateOwner(id, ownerDto);
  }

  @DeleteMapping("/{id}")
  public void deleteOwner(@PathVariable("id") int ownerId) {
    ownerService.deleteOwner(ownerId);
  }
}

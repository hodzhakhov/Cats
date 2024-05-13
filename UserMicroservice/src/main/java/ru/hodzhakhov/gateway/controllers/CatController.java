package ru.hodzhakhov.gateway.controllers;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hodzhakhov.dtos.CatColor;
import ru.hodzhakhov.dtos.CatDto;
import ru.hodzhakhov.gateway.services.CatService;

@RestController
@RequestMapping("api/cats")
public class CatController {
  private final CatService catService;

  @Autowired
  public CatController(CatService catService) {
    this.catService = catService;
  }

  @GetMapping("")
  public List<CatDto> getAllCats(
      @RequestParam(value = "color", required = false) CatColor color, Principal principal) {
    if (color != null) {
      return catService.getCatsByColor(color, principal.getName());
    } else {
      return catService.getAllCats(principal.getName());
    }
  }

  @GetMapping("/{id}")
  public CatDto getCatById(@PathVariable("id") String id, Principal principal) {
    return catService.getCatById(id, principal.getName());
  }

  @PostMapping("")
  public void addCat(@RequestBody CatDto catDto) {
    catService.addCat(catDto);
  }

  @PutMapping("/{id}")
  public void updateCat(@PathVariable("id") int id, @RequestBody CatDto catDto) {
    catService.updateCat(id, catDto);
  }

  @DeleteMapping("/{id}")
  public void deleteCat(@PathVariable("id") int catId) {
    catService.deleteCat(catId);
  }

  @PostMapping("/{id}/friend")
  public void addFriend(@PathVariable("id") int catId, @RequestParam("friendId") int friendId) {
    catService.addFriend(catId, friendId);
  }

  @DeleteMapping("/{id}/friend")
  public void removeFriend(@PathVariable("id") int catId, @RequestParam("friendId") int friendId) {
    catService.removeFriend(catId, friendId);
  }

  @PostMapping("/{id}/owner")
  public void addOwner(@PathVariable("id") int catId, @RequestParam("ownerId") int ownerId) {
    catService.addOwner(catId, ownerId);
  }
}

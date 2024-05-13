package ru.hodzhakhov.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hodzhakhov.services.CatService;
import ru.hodzhakhov.services.dtos.CatColor;
import ru.hodzhakhov.services.dtos.CatDto;

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
      @RequestParam(value = "color", required = false) CatColor color,
      @RequestParam(value = "ownerId", required = false) Optional<Integer> ownerId) {
    List<CatDto> cats;
    if (ownerId.isPresent()) {
      if (color != null) {
        cats = catService.getCatsByColorAndOwner(color, ownerId.get());
      } else {
        cats = catService.getAllCatsByOwner(ownerId.get());
      }
    } else {
      if (color != null) {
        cats = catService.getCatsByColor(color);
      } else {
        cats = catService.getAllCats();
      }
    }
    return cats;
  }

  @GetMapping("/{id}")
  public CatDto getCatById(
      @PathVariable("id") int id,
      @RequestParam(value = "ownerId", required = false) Optional<Integer> ownerId) {
    if (ownerId.isPresent()) {
      return catService.getCatByIdAndOwner(id, ownerId.get());
    } else {
      return catService.getCatById(id);
    }
  }
}

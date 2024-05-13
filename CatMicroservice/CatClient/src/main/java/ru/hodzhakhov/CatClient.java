package ru.hodzhakhov;

import java.util.List;
import ru.hodzhakhov.dtos.CatColor;
import ru.hodzhakhov.dtos.CatDto;

public interface CatClient {
  List<CatDto> getAllCats();

  CatDto getCatById(String id);

  List<CatDto> getCatsByColor(CatColor color);

  List<CatDto> getAllCatsByOwner(String ownerId);

  CatDto getCatByIdAndOwner(String id, String ownerId);

  List<CatDto> getCatsByColorAndOwner(CatColor color, String ownerId);
}

package ru.hodzhakhov.gateway.services;

import java.util.List;
import ru.hodzhakhov.dtos.CatColor;
import ru.hodzhakhov.dtos.CatDto;

public interface CatService {
  List<CatDto> getAllCats(String username);

  CatDto getCatById(String id, String username);

  List<CatDto> getCatsByColor(CatColor color, String username);

  void addCat(CatDto catDto);

  void updateCat(int id, CatDto catDto);

  void deleteCat(int id);

  void addFriend(int catId, int friendId);

  void removeFriend(int catId, int friendId);

  void addOwner(int catId, int ownerId);
}

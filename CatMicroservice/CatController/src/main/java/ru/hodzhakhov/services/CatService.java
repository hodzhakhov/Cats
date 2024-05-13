package ru.hodzhakhov.services;

import java.util.List;
import ru.hodzhakhov.services.dtos.AddOwnerDto;
import ru.hodzhakhov.services.dtos.CatColor;
import ru.hodzhakhov.services.dtos.CatDto;
import ru.hodzhakhov.services.dtos.FriendDto;

public interface CatService {
  List<CatDto> getAllCats();

  CatDto getCatById(int id);

  List<CatDto> getCatsByColor(CatColor color);

  List<CatDto> getAllCatsByOwner(int ownerId);

  CatDto getCatByIdAndOwner(int id, int ownerId);

  List<CatDto> getCatsByColorAndOwner(CatColor color, int ownerId);

  void addCat(CatDto catDto);

  void updateCat(CatDto catDto);

  void deleteCat(CatDto catDto);

  void addFriend(FriendDto friendDto);

  void removeFriend(FriendDto friendDto);

  void addOwner(AddOwnerDto addOwnerDto);
}

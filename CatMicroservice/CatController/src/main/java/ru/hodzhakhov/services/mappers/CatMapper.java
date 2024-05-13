package ru.hodzhakhov.services.mappers;

import java.util.ArrayList;
import java.util.List;
import ru.hodzhakhov.dao.entities.Cat;
import ru.hodzhakhov.services.dtos.CatDto;

public class CatMapper {
  private static List<CatDto> catFriendsToDto(Cat cat) {
    List<CatDto> friends = new ArrayList<>();
    for (Cat friend : cat.getFriends()) {
      friends.add(
          new CatDto(
              cat.getCatID(),
              cat.getName(),
              cat.getBirthDay(),
              cat.getBreed(),
              cat.getColor(),
              cat.getOwner()));
    }
    return friends;
  }

  public static Cat catDtoToEntity(CatDto catDto) {
    return new Cat(catDto.getName(), catDto.getBirthDay(), catDto.getBreed(), catDto.getColor());
  }

  public static CatDto catEntityToDto(Cat cat) {
    return new CatDto(
        cat.getCatID(),
        cat.getName(),
        cat.getBirthDay(),
        cat.getBreed(),
        cat.getColor(),
        cat.getOwner());
  }

  public static CatDto catWithoutFriends(Cat cat) {
    return new CatDto(
        cat.getCatID(),
        cat.getName(),
        cat.getBirthDay(),
        cat.getBreed(),
        cat.getColor(),
        cat.getOwner());
  }
}

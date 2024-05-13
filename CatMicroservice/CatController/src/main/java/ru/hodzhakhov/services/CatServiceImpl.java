package ru.hodzhakhov.services;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.hodzhakhov.dao.entities.Cat;
import ru.hodzhakhov.dao.repositories.CatRepository;
import ru.hodzhakhov.services.dtos.AddOwnerDto;
import ru.hodzhakhov.services.dtos.CatColor;
import ru.hodzhakhov.services.dtos.CatDto;
import ru.hodzhakhov.services.dtos.FriendDto;
import ru.hodzhakhov.services.mappers.CatMapper;

@Service
public class CatServiceImpl implements CatService {
  private final CatRepository catRepository;

  @Autowired
  public CatServiceImpl(CatRepository catRepository) {
    this.catRepository = catRepository;
  }

  @Override
  public List<CatDto> getAllCats() {
    List<CatDto> cats = new ArrayList<>();
    List<Cat> allCats = catRepository.findAll();
    for (Cat cat : allCats) {
      cats.add(CatMapper.catEntityToDto(cat));
    }
    return cats;
  }

  @Override
  public CatDto getCatById(int id) {
    return CatMapper.catEntityToDto(catRepository.getReferenceById(id));
  }

  @Override
  public List<CatDto> getCatsByColor(CatColor color) {
    List<CatDto> cats = new ArrayList<>();
    List<Cat> allCats = catRepository.findByColor(color);
    for (Cat cat : allCats) {
      cats.add(CatMapper.catEntityToDto(cat));
    }
    return cats;
  }

  @Override
  @Transactional
  public List<CatDto> getAllCatsByOwner(int ownerId) {
    List<CatDto> cats = new ArrayList<>();
    List<Cat> allCats = catRepository.findByOwner(ownerId);
    for (Cat cat : allCats) {
      cats.add(CatMapper.catEntityToDto(cat));
    }
    return cats;
  }

  @Override
  @Transactional
  public CatDto getCatByIdAndOwner(int id, int ownerId) {
    Cat cat = catRepository.getReferenceById(id);
    if (ownerId == cat.getOwner()) {
      return CatMapper.catEntityToDto(cat);
    }
    return null;
  }

  @Override
  @Transactional
  public List<CatDto> getCatsByColorAndOwner(CatColor color, int ownerId) {
    List<CatDto> cats = new ArrayList<>();
    List<Cat> allCats = catRepository.findByColorAndOwner(color, ownerId);
    for (Cat cat : allCats) {
      cats.add(CatMapper.catEntityToDto(cat));
    }
    return cats;
  }

  @Override
  @Transactional
  @KafkaListener(topics = "create_cat", groupId = "createCatGroup", containerFactory = "catFactory")
  public void addCat(CatDto catDto) {
    catRepository.save(CatMapper.catDtoToEntity(catDto));
  }

  @Override
  @Transactional
  @KafkaListener(topics = "update_cat", groupId = "updateCatGroup", containerFactory = "catFactory")
  public void updateCat(CatDto catDto) {
    Cat cat = catRepository.getReferenceById(catDto.getCatID());
    cat.setName(catDto.getName());
    cat.setBirthDay(catDto.getBirthDay());
    cat.setBreed(catDto.getBreed());
    cat.setColor(catDto.getColor());
    catRepository.save(cat);
  }

  @Override
  @Transactional
  @KafkaListener(topics = "delete_cat", groupId = "deleteCatGroup", containerFactory = "catFactory")
  public void deleteCat(CatDto catDto) {
    Cat cat = catRepository.getReferenceById(catDto.getCatID());
    for (Cat friend : cat.getFriends()) {
      friend.getFriends().remove(cat);
      catRepository.save(friend);
    }
    catRepository.delete(cat);
  }

  @Override
  @Transactional
  @KafkaListener(
      topics = "friend_cat",
      groupId = "friendCatGroup",
      containerFactory = "friendFactory")
  public void addFriend(FriendDto friendDto) {
    Cat cat = catRepository.getReferenceById(friendDto.getCatId());
    Cat friend = catRepository.getReferenceById(friendDto.getFriendId());

    if (!cat.getFriends().contains(friend)) {
      cat.getFriends().add(friend);
    }
    if (!friend.getFriends().contains(cat)) {
      friend.getFriends().add(cat);
    }
    catRepository.save(cat);
    catRepository.save(friend);
  }

  @Override
  @Transactional
  @KafkaListener(
      topics = "unfriend_cat",
      groupId = "friendCatGroup",
      containerFactory = "friendFactory")
  public void removeFriend(FriendDto friendDto) {
    Cat cat = catRepository.getReferenceById(friendDto.getCatId());
    Cat friend = catRepository.getReferenceById(friendDto.getFriendId());

    cat.getFriends().remove(friend);
    friend.getFriends().remove(cat);
    catRepository.save(cat);
    catRepository.save(friend);
  }

  @Override
  @Transactional
  @KafkaListener(
      topics = "add_owner",
      groupId = "addOwnerGroup",
      containerFactory = "addOwnerFactory")
  public void addOwner(AddOwnerDto addOwnerDto) {
    Cat cat = catRepository.getReferenceById(addOwnerDto.getCatId());
    cat.setOwner(addOwnerDto.getOwnerId());
    catRepository.save(cat);
  }
}

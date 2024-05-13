package ru.hodzhakhov.services;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.hodzhakhov.dao.entities.Owner;
import ru.hodzhakhov.dao.repositories.OwnerRepository;
import ru.hodzhakhov.services.dtos.OwnerDto;
import ru.hodzhakhov.services.mappers.OwnerMapper;

@Service
public class OwnerServiceImpl implements OwnerService {
  private final OwnerRepository ownerRepository;

  @Autowired
  public OwnerServiceImpl(OwnerRepository ownerRepository) {
    this.ownerRepository = ownerRepository;
  }

  @Override
  @Transactional
  public List<OwnerDto> getAllOwners() {
    List<OwnerDto> owners = new ArrayList<>();
    for (Owner owner : ownerRepository.findAll()) {
      owners.add(OwnerMapper.ownerEntityToDto(owner));
    }
    return owners;
  }

  @Override
  @Transactional
  public OwnerDto getOwnerById(int id) {
    Owner owner = ownerRepository.getReferenceById(id);
    return OwnerMapper.ownerEntityToDto(owner);
  }

  @Override
  @Transactional
  @KafkaListener(
      topics = "create_owner",
      groupId = "createOwnerGroup",
      containerFactory = "ownerFactory")
  public void addOwner(OwnerDto ownerDto) {
    ownerRepository.save(OwnerMapper.ownerDtoToEntity(ownerDto));
  }

  @Override
  @Transactional
  @KafkaListener(
      topics = "update_owner",
      groupId = "updateOwnerGroup",
      containerFactory = "ownerFactory")
  public void updateOwner(OwnerDto ownerDto) {
    Owner owner = ownerRepository.getReferenceById(ownerDto.getOwnerID());
    owner.setName(ownerDto.getName());
    owner.setBirthDay(ownerDto.getBirthDay());
    ownerRepository.save(owner);
  }

  @Override
  @Transactional
  @KafkaListener(
      topics = "delete_owner",
      groupId = "deleteOwnerGroup",
      containerFactory = "ownerFactory")
  public void deleteOwner(OwnerDto ownerDto) {
    Owner owner = ownerRepository.getReferenceById(ownerDto.getOwnerID());
    ownerRepository.delete(owner);
  }
}

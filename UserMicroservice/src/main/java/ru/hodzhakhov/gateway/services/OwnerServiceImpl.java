package ru.hodzhakhov.gateway.services;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.hodzhakhov.OwnerClient;
import ru.hodzhakhov.dtos.OwnerDto;
import ru.hodzhakhov.users.dao.entities.Role;
import ru.hodzhakhov.users.dao.entities.User;
import ru.hodzhakhov.users.dao.repositories.UserRepository;

@Service
public class OwnerServiceImpl implements OwnerService {
  private final OwnerClient ownerClient;
  private final UserRepository userRepository;
  private final KafkaTemplate<String, OwnerDto> ownerKafkaTemplate;

  @Autowired
  public OwnerServiceImpl(
      OwnerClient ownerClient,
      UserRepository userRepository,
      KafkaTemplate<String, OwnerDto> ownerKafkaTemplate) {
    this.ownerClient = ownerClient;
    this.userRepository = userRepository;
    this.ownerKafkaTemplate = ownerKafkaTemplate;
  }

  @Override
  @Transactional
  public List<OwnerDto> getAllOwners(String username) {
    User user = userRepository.findByUsername(username);
    if (user.getRoles().contains(Role.ROLE_ADMIN)) {
      return ownerClient.getAllOwners();
    }
    List<OwnerDto> allOwners = new ArrayList<>();
    allOwners.add(ownerClient.getOwnerById(Integer.toString(user.getOwner())));
    return allOwners;
  }

  @Override
  @Transactional
  public OwnerDto getOwnerById(String id, String username) {
    User user = userRepository.findByUsername(username);
    if (user.getRoles().contains(Role.ROLE_ADMIN)) {
      return ownerClient.getOwnerById(id);
    }
    return null;
  }

  @Override
  @Transactional
  public void addOwner(OwnerDto ownerDto) {
    ownerKafkaTemplate.send("create_owner", ownerDto);
  }

  @Override
  @Transactional
  public void updateOwner(int id, OwnerDto ownerDto) {
    ownerDto.setOwnerID(id);
    ownerKafkaTemplate.send("update_owner", ownerDto);
  }

  @Override
  @Transactional
  public void deleteOwner(int id) {
    OwnerDto ownerDto = new OwnerDto();
    ownerDto.setOwnerID(id);
    ownerKafkaTemplate.send("delete_owner", ownerDto);
  }
}

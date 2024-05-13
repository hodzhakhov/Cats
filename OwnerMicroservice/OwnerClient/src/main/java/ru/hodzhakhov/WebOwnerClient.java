package ru.hodzhakhov;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hodzhakhov.dtos.OwnerDto;

@Service
public class WebOwnerClient implements OwnerClient {
  private final WebClient webClient;

  @Autowired
  public WebOwnerClient(@Qualifier("WebOwnerClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public List<OwnerDto> getAllOwners() {
    return this.webClient.get().retrieve().bodyToFlux(OwnerDto.class).collectList().block();
  }

  @Override
  public OwnerDto getOwnerById(String id) {
    return this.webClient
        .get()
        .uri(String.join("", "/", id))
        .retrieve()
        .bodyToMono(OwnerDto.class)
        .block();
  }
}

package ru.hodzhakhov;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.hodzhakhov.dtos.CatColor;
import ru.hodzhakhov.dtos.CatDto;

@Service
public class WebCatClient implements CatClient {
  private final WebClient webClient;

  @Autowired
  public WebCatClient(@Qualifier("WebCatClient") WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  public List<CatDto> getAllCats() {
    return this.webClient.get().retrieve().bodyToFlux(CatDto.class).collectList().block();
  }

  @Override
  public CatDto getCatById(String id) {
    return this.webClient
        .get()
        .uri(String.join("", "/", id))
        .retrieve()
        .bodyToMono(CatDto.class)
        .block();
  }

  @Override
  public List<CatDto> getCatsByColor(CatColor color) {
    return this.webClient
        .get()
        .uri(String.join("", "?color=", color.toString()))
        .retrieve()
        .bodyToFlux(CatDto.class)
        .collectList()
        .block();
  }

  @Override
  public List<CatDto> getAllCatsByOwner(String ownerId) {
    return this.webClient
        .get()
        .uri(String.join("", "?ownerId=", ownerId))
        .retrieve()
        .bodyToFlux(CatDto.class)
        .collectList()
        .block();
  }

  @Override
  public CatDto getCatByIdAndOwner(String id, String ownerId) {
    return this.webClient
        .get()
        .uri(String.join("", "/", id, "?ownerId=", ownerId))
        .retrieve()
        .bodyToMono(CatDto.class)
        .block();
  }

  @Override
  public List<CatDto> getCatsByColorAndOwner(CatColor color, String ownerId) {
    return this.webClient
        .get()
        .uri(String.join("", "?color=", color.toString(), "&ownerId=", ownerId))
        .retrieve()
        .bodyToFlux(CatDto.class)
        .collectList()
        .block();
  }
}

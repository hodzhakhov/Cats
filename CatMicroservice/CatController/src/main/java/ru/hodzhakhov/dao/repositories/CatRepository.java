package ru.hodzhakhov.dao.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hodzhakhov.dao.entities.Cat;
import ru.hodzhakhov.services.dtos.CatColor;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer> {
  List<Cat> findByColor(CatColor color);

  List<Cat> findByOwner(int owner);

  List<Cat> findByColorAndOwner(CatColor color, int owner);
}

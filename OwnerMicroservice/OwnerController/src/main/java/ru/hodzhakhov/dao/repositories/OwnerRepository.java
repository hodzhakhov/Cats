package ru.hodzhakhov.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hodzhakhov.dao.entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {}

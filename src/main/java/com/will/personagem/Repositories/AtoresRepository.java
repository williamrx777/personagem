package com.will.personagem.Repositories;

import com.will.personagem.Entities.Atores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtoresRepository extends JpaRepository<Atores,Long> {



}

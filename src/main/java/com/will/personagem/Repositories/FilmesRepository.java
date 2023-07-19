package com.will.personagem.Repositories;

import com.will.personagem.Entities.Filmes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FilmesRepository extends JpaRepository<Filmes,Long> {



}

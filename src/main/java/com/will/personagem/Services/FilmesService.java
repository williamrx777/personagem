package com.will.personagem.Services;

import com.will.personagem.Entities.Filmes;
import com.will.personagem.Repositories.FilmesRepository;
import com.will.personagem.Dtos.FilmesDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class FilmesService {

    @Autowired
    FilmesRepository filmesRepository;


    @Transactional
    public List<FilmesDTO> findAll(){
        List<Filmes> result = filmesRepository.findAll();
        return result.stream().map(x -> new FilmesDTO(x)).toList();
    }
    @Transactional
    public FilmesDTO findById(@PathVariable Long id){
        Filmes result = filmesRepository.findById(id).get();
        return new FilmesDTO(result);
    }

    @Transactional
    public Filmes postFilmes(Filmes filmes){
        return filmesRepository.save(filmes);
    }
    @Transactional
    public Optional<Filmes> getFilme(@PathVariable Long id){
        return filmesRepository.findById(id);
    }

    @Transactional
    public Optional<Filmes> updateFilme(Long id, Filmes update){
        Optional<Filmes> filmesExistent = filmesRepository.findById(id);
        if(filmesExistent.isPresent()){
            update.setId(id);
            return Optional.of(filmesRepository.save(update));
        }else{
            return Optional.empty();
        }
    }
    @Transactional
    public boolean deleteFilmes(Long id){
        Optional<Filmes> filmesExistente = filmesRepository.findById(id);
        if(filmesExistente.isPresent()){
            filmesRepository.delete(filmesExistente.get());
            return true;
        }else{
            return false;
        }
    }



}

package com.will.personagem.Services;

import com.will.personagem.Entities.Atores;
import com.will.personagem.Repositories.AtoresRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class AtoresService {

    @Autowired
    AtoresRepository atoresRepository;
    @Transactional
    public List<Atores> findAll(){
        return atoresRepository.findAll();
    }
    @Transactional
    public Atores postFilmes(Atores atores){

        return atoresRepository.save(atores);
    }
    @Transactional
    public Optional<Atores> getAtor(@PathVariable Long id){
        return atoresRepository.findById(id);
    }
    @Transactional
    public Optional<Atores> updateAtor(Long id, Atores update){
        Optional<Atores> atorExistent = atoresRepository.findById(id);
        if(atorExistent.isPresent()){
            update.setId(id);
            return Optional.of(atoresRepository.save(update));
        }else{
            return Optional.empty();
        }
    }
    @Transactional
    public boolean deleteAtor(Long id){
        Optional<Atores> atorExistente = atoresRepository.findById(id);
        if(atorExistente.isPresent()){
            atoresRepository.delete(atorExistente.get());
            return true;
        }else{
            return false;
        }
    }

}

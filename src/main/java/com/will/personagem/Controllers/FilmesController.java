package com.will.personagem.Controllers;

import com.will.personagem.Dtos.FilmesDTO;
import com.will.personagem.Entities.Atores;
import com.will.personagem.Repositories.FilmesRepository;
import com.will.personagem.Entities.Filmes;
import com.will.personagem.Repositories.AtoresRepository;
import com.will.personagem.Services.AtoresService;
import com.will.personagem.Services.FilmesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/filmes")
@Tag(name = "filmes")
public class FilmesController {

    @Autowired
    private FilmesService filmesService;
    @Autowired
    private AtoresService atoresService;

    @Autowired
    private FilmesRepository filmesRepository;
    @Autowired
    private AtoresRepository atoresRepository;


    @Operation(summary = "Buscar todos os filmes")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping
    public List<FilmesDTO> findAll(){
        var result = filmesRepository.findAll();
        return result.stream().map(x -> new FilmesDTO(x)).toList();
    }
    @GetMapping("/{filmeId}/atores/{atorId}")
    @Transactional
    public ResponseEntity<String> getAtorToFilme(@PathVariable Long filmeId, @PathVariable Long atorId) {
        Filmes filme = filmesRepository.findById(filmeId).orElse(null);
        Atores ator = atoresRepository.findById(atorId).orElse(null);

        if (filme != null && ator != null) {
            if (filme.getAtores().contains(ator)) {
                return ResponseEntity.ok("O " + ator.getName() + "  " + ator.getImage() + " está associado ao filme " + filme.getName() + ".");
            } else {
                return ResponseEntity.ok("O ator não está associado ao filme.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Publicar filme")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Publicação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar publicação de filmes"),
    })
    @PostMapping
    public FilmesDTO postFilme(@RequestBody FilmesDTO filmesDTO){
        Filmes filme = FilmesDTO.toEntity(filmesDTO);
        Filmes novoFilme = filmesService.postFilmes(filme);
        return FilmesDTO.toDto(novoFilme);
    }
    @Operation(summary = "Publicar filme e ator")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Publicação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar publicação de filmes"),
    })
    @PostMapping("/{filmeId}/atores/{atorId}")
    @Transactional
    public void addAtorToFilme(@PathVariable Long filmeId, @PathVariable Long atorId) {
        Filmes filme = filmesRepository.findById(filmeId).orElse(null);
        Atores ator = atoresRepository.findById(atorId).orElse(null);
        if (filme != null && ator != null) {
            filme.getAtores().add(ator);
            filmesRepository.save(filme);
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Operation(summary = "Buscar filme")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<FilmesDTO> getFilme(@PathVariable Long id){
        Optional<Filmes> filme = filmesService.getFilme(id);
        if(filme.isPresent()){
            FilmesDTO filmesDTO = FilmesDTO.toDto(filme.get());

            return ResponseEntity.ok(filmesDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "atualizar filme")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar atualização dos dados"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<FilmesDTO> updateFilme(@PathVariable Long id, @RequestBody FilmesDTO filmesDTO){
        Filmes filme = FilmesDTO.toEntity(filmesDTO);
        Optional<Filmes> updateFilme = filmesService.updateFilme(id, filme);
        if(updateFilme.isPresent()){
            FilmesDTO filmeUpdateDto = FilmesDTO.toDto(updateFilme.get());
            return ResponseEntity.ok(filmeUpdateDto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Deletar filme")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Delete realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar delete dos dados"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilme(@PathVariable Long id){
        if(filmesService.deleteFilmes(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "Deletar filme e ator")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Delete realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar delete dos dados"),
    })
    @DeleteMapping("/{filmeId}/atores/{atorId}")
    @Transactional
    public void removeAtorFromFilme(@PathVariable Long filmeId, @PathVariable Long atorId) {
        Filmes filme = filmesRepository.findById(filmeId).orElse(null);
        Atores ator = atoresRepository.findById(atorId).orElse(null);
        if (filme != null && ator != null) {
            filme.getAtores().remove(ator);
            filmesRepository.save(filme);
        }
    }

}

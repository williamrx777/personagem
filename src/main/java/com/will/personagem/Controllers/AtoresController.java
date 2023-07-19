package com.will.personagem.Controllers;

import com.will.personagem.Dtos.AtoresDTO;
import com.will.personagem.Entities.Atores;
import com.will.personagem.Services.AtoresService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/atores")
@Tag(name = "atores")
public class AtoresController {

    @Autowired
    private AtoresService atoresService;


    @Operation(summary = "Buscar todos os atores")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping
    public List<AtoresDTO> findAll(){
        List<Atores> atores = atoresService.findAll();
        return atores.stream().map(AtoresDTO::toDto).collect(Collectors.toList());
    }



    @Operation(summary = "Publicar ator")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Publicação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar publicação de filmes"),
    })
    @PostMapping
    public AtoresDTO postAtor(@RequestBody AtoresDTO atoresDTO){
        Atores atores = AtoresDTO.toEntity(atoresDTO);
        Atores novoAtor = atoresService.postFilmes(atores);
        return AtoresDTO.toDto(novoAtor);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @Operation(summary = "Buscar ator")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<AtoresDTO> getAtor(@PathVariable Long id){
        Optional<Atores> ator = atoresService.getAtor(id);
        if(ator.isPresent()){
            AtoresDTO atoresDTO = AtoresDTO.toDto(ator.get());
            return ResponseEntity.ok(atoresDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "atualizar ator")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar atualização dos dados"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<AtoresDTO> updateAtor(@PathVariable Long id, @RequestBody AtoresDTO atoresDTO){
        Atores atores = AtoresDTO.toEntity(atoresDTO);
        Optional<Atores> updateAtor = atoresService.updateAtor(id, atores);
        if(updateAtor.isPresent()){
            AtoresDTO atoresUpdateDto = AtoresDTO.toDto(updateAtor.get());
            return ResponseEntity.ok(atoresUpdateDto);
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
        if(atoresService.deleteAtor(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}

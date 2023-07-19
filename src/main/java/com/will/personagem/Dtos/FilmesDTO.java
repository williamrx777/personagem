package com.will.personagem.Dtos;

import com.will.personagem.Entities.Filmes;

import org.springframework.beans.BeanUtils;

public class FilmesDTO {

    private String name;
    private String description;
    private String image;


    public FilmesDTO() {
    }

    public FilmesDTO(Filmes entity) {
        BeanUtils.copyProperties(entity, this);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public static FilmesDTO toDto(Filmes filmes){
        FilmesDTO filmeDto = new FilmesDTO();

        filmeDto.setName(filmes.getName());
        filmeDto.setDescription(filmes.getDescription());
        filmeDto.setImage(filmes.getImage());
        return filmeDto;
    }

    public static Filmes toEntity(FilmesDTO filmesDTO){
        Filmes filmes = new Filmes();

        filmes.setName(filmesDTO.getName());
        filmes.setDescription(filmesDTO.getDescription());
        filmes.setImage(filmesDTO.getImage());
        return filmes;
    }

    @Override
    public String toString() {
        return "FilmesDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

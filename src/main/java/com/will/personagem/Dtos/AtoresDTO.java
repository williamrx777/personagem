package com.will.personagem.Dtos;

import com.will.personagem.Entities.Atores;
import org.springframework.beans.BeanUtils;

public class AtoresDTO {


    private String name;
    private String image;


    public AtoresDTO() {
    }

    public AtoresDTO(Atores entity) {
        BeanUtils.copyProperties(entity, this);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public static AtoresDTO toDto(Atores atores){
        AtoresDTO atoresDTO = new AtoresDTO();

        atoresDTO.setName(atores.getName());
        atoresDTO.setImage(atores.getImage());
        return atoresDTO;
    }

    public static Atores toEntity(AtoresDTO atoresDTO){
        Atores atores = new Atores();

        atores.setName(atoresDTO.getName());
        atores.setImage(atoresDTO.image);
        return atores;
    }

    @Override
    public String toString() {
        return "AtoresDTO{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

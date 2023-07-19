package com.will.personagem.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "td_atores")
public class Atores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    @ManyToMany(mappedBy = "atores")
    private List<Filmes> filmes = new ArrayList<>();
    public Atores() {
    }

    public Atores(Long id, String name, String image, List<Filmes> filmes) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.filmes = filmes;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public List<Filmes> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filmes> filmes) {
        this.filmes = filmes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atores atores = (Atores) o;
        return Objects.equals(id, atores.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

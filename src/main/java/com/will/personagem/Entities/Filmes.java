package com.will.personagem.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "td_filmes")
public class Filmes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;

    @ManyToMany
    @JoinTable(name = "atores_filmes",
            joinColumns = {@JoinColumn(name = "filme_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ator_id", referencedColumnName = "id")})
    private List<Atores> atores = new ArrayList<>();

    public Filmes() {
    }

    public Filmes(Long id, String name, String description, String image, List<Atores> atores) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.atores = atores;
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

    public List<Atores> getAtores() {
        return atores;
    }

    public void setAtores(List<Atores> atores) {
        this.atores = atores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filmes filmes = (Filmes) o;
        return Objects.equals(id, filmes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

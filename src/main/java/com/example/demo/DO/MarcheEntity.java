package com.example.demo.DO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="MARCHE")
public class MarcheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nom;
    @Column(name = "date_marche")
    private Date date;
    @Column
    private String description;
    @Column
    private String url_image;
    @ManyToMany
    @JoinTable(name = "EMPLACEMENT_MARCHE",
            joinColumns = {@JoinColumn(name = "ID_MARCHE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_EMPLACEMENT")})
    private List<EmplacementEntity> emplacements;

    public MarcheEntity(Integer id, String nom, Date date, String description,String url_image, List<EmplacementEntity> emplacements) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.url_image = url_image;
        this.emplacements = emplacements;
    }

    public MarcheEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public List<EmplacementEntity> getEmplacements() {
        return emplacements;
    }

    public void setEmplacements(List<EmplacementEntity> emplacements) {
        this.emplacements = emplacements;
    }
}

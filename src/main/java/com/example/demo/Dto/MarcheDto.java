package com.example.demo.Dto;

import com.example.demo.DO.MarcheEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MarcheDto {
    private Integer id;
    @NotEmpty(message = "Ne peut pas être vide")
    private String nom;
    @NotNull(message = "Ne peut pas être vide")
    private Date date;
    private String description;
    private String url_image;
    private List<EmplacementDto> emplacements;


    public MarcheDto() {
    }

    public MarcheDto(Integer id, @NotEmpty(message = "Can't be empty") String nom, @NotEmpty(message = "Can't be empty") Date date, String description,String url_image, List<EmplacementDto> emplacementDtoList) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.url_image = url_image;
        this.emplacements = emplacementDtoList;
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

    public List<EmplacementDto> getEmplacements() {
        return emplacements;
    }

    public void setEmplacements(List<EmplacementDto> emplacements) {
        this.emplacements = emplacements;
    }

    //convertir entity En dto
    public MarcheDto(MarcheEntity marcheEntity) {
        this.id = marcheEntity.getId();
        this.nom = marcheEntity.getNom();
        this.date = marcheEntity.getDate();
        this.description = marcheEntity.getDescription();
        this.url_image = marcheEntity.getUrl_image();
        if(marcheEntity.getEmplacements() != null){
        emplacements = marcheEntity.getEmplacements().stream()
                .map(EmplacementDto::new)
                .collect(Collectors.toList());
        }
    }

//convertir Dto En entity
    public MarcheEntity market() {
        MarcheEntity marcheEntity = new MarcheEntity();
        marcheEntity.setId(id);
        marcheEntity.setNom(nom);
        marcheEntity.setDate(date);
        marcheEntity.setDescription(description);
        marcheEntity.setUrl_image(url_image);
        if (emplacements != null) {
            marcheEntity.setEmplacements(emplacements.stream()
                    .map(EmplacementDto::emplacements)
                    .collect(Collectors.toList()));
        }
        return marcheEntity;
    }
}


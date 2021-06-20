package com.example.demo.Dto;


import com.example.demo.DO.EmplacementEntity;

public class EmplacementDto {

    private Integer id;
    private Integer numero;
    private Integer numero_de_rue;
    private String adresse;

    public EmplacementDto(Integer id, Integer numero, Integer numero_de_rue, String adresse) {
        this.id = id;
        this.numero = numero;
        this.numero_de_rue = numero_de_rue;
        this.adresse = adresse;
    }

    public EmplacementDto(EmplacementEntity emplacementEntity) {
        this.id = emplacementEntity.getId();
        this.numero = emplacementEntity.getNumero();
        this.numero_de_rue = emplacementEntity.getNumero_de_rue();
        this.adresse = emplacementEntity.getAdresse();
    }

    public EmplacementEntity emplacements() {
        EmplacementEntity emplacementEntity = new EmplacementEntity();
        emplacementEntity.setId(id);
        emplacementEntity.setAdresse(adresse);
        emplacementEntity.setNumero(numero);
        emplacementEntity.setNumero_de_rue(numero_de_rue);
        return emplacementEntity;
    }

    public EmplacementDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero_de_rue() {
        return numero_de_rue;
    }

    public void setNumero_de_rue(Integer numero_de_rue) {
        this.numero_de_rue = numero_de_rue;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}

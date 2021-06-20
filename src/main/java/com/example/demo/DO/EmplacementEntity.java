package com.example.demo.DO;

import javax.persistence.*;

@Entity
@Table(name="EMPLACEMENT")
public class EmplacementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer numero;
    @Column
    private Integer numero_de_rue;
    @Column
    private String adresse;

    public EmplacementEntity(Integer id, Integer numero, Integer numero_de_rue, String adresse) {
        this.id = id;
        this.numero = numero;
        this.numero_de_rue = numero_de_rue;
        this.adresse = adresse;
    }

    public EmplacementEntity() {
    }

    public EmplacementEntity(EmplacementEntity emplacementEntity) {
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

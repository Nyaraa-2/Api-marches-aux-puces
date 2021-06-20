package com.example.demo.Dto;
import com.example.demo.DO.UtilisateurEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UtilisateurDto {
    private Integer id;
    @NotEmpty (message = "Can't be empty")
    private String nom;
    @NotEmpty (message = "Can't be empty")
    private String prenom;
    @NotEmpty (message = "Can't be empty")
    private String email;
    @NotNull(message = "Can't be empty")
    @Min(10)
    private String telephone;
    @NotNull(message = "Can't be empty")
    @Min(1)
    private Integer numero_de_rue;
    @NotEmpty (message = "Can't be empty")
    private String adresse;

    public UtilisateurDto(Integer id, @NotEmpty(message = "Can't be empty") String nom, @NotEmpty(message = "Can't be empty") String prenom, @NotEmpty(message = "Can't be empty") String email, @NotNull(message = "Can't be empty") @Min(10) String telephone, @NotNull(message = "Can't be empty") @Min(1) Integer numero_de_rue, @NotEmpty(message = "Can't be empty") String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.numero_de_rue = numero_de_rue;
        this.adresse = adresse;
    }

    public UtilisateurDto(UtilisateurEntity utilisateurEntity) {
        this.id = utilisateurEntity.getId();
        this.nom = utilisateurEntity.getNom();
        this.prenom = utilisateurEntity.getPrenom();
        this.email = utilisateurEntity.getEmail();
        this.telephone = utilisateurEntity.getTelephone();
        this.numero_de_rue = utilisateurEntity.getNumero_de_rue();
        this.adresse = utilisateurEntity.getAdresse();
    }

    public UtilisateurEntity toUtilisateur() {
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setId(id);
        utilisateurEntity.setNom(nom);
        utilisateurEntity.setPrenom(prenom);
        utilisateurEntity.setEmail(email);
        utilisateurEntity.setTelephone(telephone);
        utilisateurEntity.setNumero_de_rue(numero_de_rue);
        utilisateurEntity.setAdresse(adresse);
        return utilisateurEntity;
    }


    public UtilisateurDto() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

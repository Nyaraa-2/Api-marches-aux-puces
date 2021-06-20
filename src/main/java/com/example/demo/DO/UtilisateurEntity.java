package com.example.demo.DO;
import javax.persistence.*;

@Entity
@Table(name="UTILISATEUR")
public class UtilisateurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String email;
    @Column
    private String telephone;
    @Column
    private Integer numero_de_rue;
    @Column
    private String adresse;

    public UtilisateurEntity(Integer id, String nom, String prenom, String email, String telephone, Integer numero_de_rue, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.numero_de_rue = numero_de_rue;
        this.adresse = adresse;
    }

    public UtilisateurEntity() {
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

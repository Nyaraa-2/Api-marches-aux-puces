package com.example.demo.DO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="RESERVATION")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer numero;

    @Column
    private Date date_reservation;

    @ManyToOne
    @JoinColumn (name = "ID_UTILISATEUR")
    private UtilisateurEntity utilisateurEntity;

    @ManyToOne
    @JoinColumn (name = "ID_EMPLACEMENT")
    private EmplacementEntity emplacementEntity;

    @ManyToOne
    @JoinColumn (name = "ID_MARCHE")
    private MarcheEntity marcheEntity;

    public ReservationEntity(Integer id, Integer numero, Date dateReservation, UtilisateurEntity utilisateurEntity, EmplacementEntity emplacementEntity, MarcheEntity marcheEntity) {
        this.id = id;
        this.numero = numero;
        this.date_reservation = dateReservation;
        this.utilisateurEntity = utilisateurEntity;
        this.emplacementEntity = emplacementEntity;
        this.marcheEntity = marcheEntity;
    }

    public ReservationEntity() {
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

    public Date getDateReservation() {
        return date_reservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.date_reservation = dateReservation;
    }

    public UtilisateurEntity getUtilisateurEntity() {
        return utilisateurEntity;
    }

    public void setUtilisateurEntity(UtilisateurEntity utilisateurEntity) {
        this.utilisateurEntity = utilisateurEntity;
    }

    public EmplacementEntity getEmplacementEntity() {
        return emplacementEntity;
    }

    public void setEmplacementEntity(EmplacementEntity emplacementEntity) {
        this.emplacementEntity = emplacementEntity;
    }

    public MarcheEntity getMarcheEntity() {
        return marcheEntity;
    }

    public void setMarcheEntity(MarcheEntity marcheEntity) {
        this.marcheEntity = marcheEntity;
    }
}

package com.example.demo.Dto;

import com.example.demo.DO.ReservationEntity;

import java.util.Date;
import java.util.Random;

public class ReservationDto {

    private Integer id;
    private Integer numero;
    private Date date_reservation;
    private UtilisateurDto utilisateurDto;
    private EmplacementDto emplacementDto;
    private MarcheDto marcheDto;

    public ReservationDto(Integer id, Integer numero, Date date_reservation, UtilisateurDto utilisateurDto, EmplacementDto emplacementDto, MarcheDto marcheDto) {
        this.id = id;
        this.numero = numero;
        this.date_reservation = date_reservation;
        this.utilisateurDto = utilisateurDto;
        this.emplacementDto = emplacementDto;
        this.marcheDto = marcheDto;
    }

    public ReservationDto(ReservationEntity reservationEntity) {
        this.id = reservationEntity.getId();
        this.numero = reservationEntity.getNumero();
        this.date_reservation = reservationEntity.getDateReservation();
        this.utilisateurDto = new UtilisateurDto(reservationEntity.getUtilisateurEntity());
        this.emplacementDto = new EmplacementDto(reservationEntity.getEmplacementEntity());
        this.marcheDto = new MarcheDto(reservationEntity.getMarcheEntity());
    }

    public ReservationDto() {
    }

    public ReservationEntity fromReservation(){
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(id);
        Random numeroDeReservation = new Random();
        int low = 1;
        int high = 150;
        reservationEntity.setNumero(numeroDeReservation.nextInt(high - low) + low);
        reservationEntity.setDateReservation(date_reservation);
        if(utilisateurDto != null){
            reservationEntity.setUtilisateurEntity(utilisateurDto.toUtilisateur());
        }
        if(emplacementDto != null){
            reservationEntity.setEmplacementEntity(emplacementDto.emplacements());
        }
        if(marcheDto != null){
            reservationEntity.setMarcheEntity(marcheDto.market());
        }
        return reservationEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    public UtilisateurDto getUtilisateurDto() {
        return utilisateurDto;
    }

    public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
        this.utilisateurDto = utilisateurDto;
    }

    public EmplacementDto getEmplacementDto() {
        return emplacementDto;
    }

    public void setEmplacementDto(EmplacementDto emplacementDto) {
        this.emplacementDto = emplacementDto;
    }

    public MarcheDto getMarcheDto() {
        return marcheDto;
    }

    public void setMarcheDto(MarcheDto marcheDto) {
        this.marcheDto = marcheDto;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}

package com.example.demo.Service;

import com.example.demo.Dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> getReservationByIdMarket(Integer id);

    ReservationDto getReservationById(Integer id);

    ReservationDto editReservation(ReservationDto reservationDto);

    ReservationDto createReservation(ReservationDto reservationDto);

    List<ReservationDto> getAllReservations();

    void deleteReservationDto(Integer id);
}

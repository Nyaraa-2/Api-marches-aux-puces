package com.example.demo.Service.Impl;

import com.example.demo.Dto.ReservationDto;
import com.example.demo.DO.ReservationEntity;
import com.example.demo.DAO.ReservationRepository;
import com.example.demo.Service.ReservationService;
import com.example.demo.Status.NotFoundExceptionMarche;
import com.example.demo.Status.NotFoundExceptionReserve;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ReservationDto> getReservationByIdMarket(Integer id) {
        return reservationRepository.findByMarcheEntity_id(id)
                .stream()
                .map(ReservationDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReservationDto(Integer id) {
        List<ReservationDto> reservationDtoList = getAllReservations();
        boolean findReservation = reservationDtoList.stream()
                .anyMatch(reservationDto -> reservationDto.getId().equals(id));
        if(!findReservation){
            throw new NotFoundExceptionReserve();
        }
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationDto getReservationById(Integer id) {
        ReservationEntity reservationEntity = reservationRepository.findById(id).orElseThrow(NotFoundExceptionMarche::new);
        return new ReservationDto(reservationEntity);
    }

    @Override
    public ReservationDto editReservation(ReservationDto reservationDto) {
        return new ReservationDto(reservationRepository.save(reservationDto.fromReservation()));
    }

    @Override
    public ReservationDto createReservation(ReservationDto reservationDto) {
        ReservationEntity reservationEntity = reservationDto.fromReservation();
        reservationEntity.setDateReservation(new Date());
        return new ReservationDto(reservationRepository.save(reservationEntity));
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(),false)
                .map(ReservationDto::new)
                .collect(Collectors.toList());
    }
}

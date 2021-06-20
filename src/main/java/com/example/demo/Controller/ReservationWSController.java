package com.example.demo.Controller;

import com.example.demo.Dto.ReservationDto;
import com.example.demo.Service.ReservationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/reservations")
@Validated
@RestController
public class ReservationWSController {
    private ReservationService reservationService;

    public ReservationWSController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationDto> getReservation(@RequestParam(value = "Marche", required = false) Integer id) {
        if (id != null) {
            return reservationService.getReservationByIdMarket(id);
        }
        return reservationService.getAllReservations();
    }

    @DeleteMapping("/{id}")
    public void deleteReservatiobn(@PathVariable("id") Integer id) {
        reservationService.deleteReservationDto(id);
    }

    @GetMapping("/{id}")
    public ReservationDto getReservationById(@PathVariable("id") Integer id) {
        return reservationService.getReservationById(id);
    }

    @PutMapping("/{id}")
    public ReservationDto editReservation(@PathVariable("id") Integer id, @RequestBody ReservationDto reservationDto) {
        reservationDto.setId(id);
        return reservationService.editReservation(reservationDto);
    }

    @PostMapping
    public ReservationDto createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        return reservationService.createReservation(reservationDto);
    }

}

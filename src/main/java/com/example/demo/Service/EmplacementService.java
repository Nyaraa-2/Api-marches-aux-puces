package com.example.demo.Service;

import com.example.demo.Dto.EmplacementDto;
import com.example.demo.Dto.ReservationDto;

import java.util.List;

public interface EmplacementService {
    List<EmplacementDto> findAll();
    EmplacementDto findById(Integer id);
}

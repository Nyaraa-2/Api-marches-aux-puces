package com.example.demo.Service;

import com.example.demo.Dto.EmplacementDto;
import com.example.demo.Dto.MarcheDto;
import com.example.demo.Dto.ReservationDto;

import java.util.List;
//methode appellé par le controler
public interface MarcheService {
    MarcheDto createMarket(MarcheDto marcheDto); //create
    MarcheDto getMarketById(Integer id); //read
    MarcheDto editMarket(MarcheDto marcheDto); //update
    void deleteById(Integer integer); //delete
    List<MarcheDto> getAllMarket(Boolean marcheFutur, Boolean marcheAncien);
    List<EmplacementDto> getEmplacementMarche(Integer id,Boolean disponible);
}

package com.example.demo.Service.Impl;

import com.example.demo.DAO.MarcheJdbcRepository;
import com.example.demo.Dto.EmplacementDto;
import com.example.demo.Dto.MarcheDto;
import com.example.demo.DO.EmplacementEntity;
import com.example.demo.DO.MarcheEntity;
import com.example.demo.DAO.MarcheRepository;
import com.example.demo.Service.MarcheService;
import com.example.demo.Status.BadRequestException;
import com.example.demo.Status.NotFoundExceptionMarche;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.StreamSupport;

//transforme les entity en DTO
@Service
public class MarcheServiceImpl implements MarcheService {
    private MarcheRepository marcheRepository;
    private MarcheJdbcRepository marcheJdbcRepository;

    public MarcheServiceImpl(MarcheRepository marcheRepository, MarcheJdbcRepository marcheJdbcRepository) {
        this.marcheRepository = marcheRepository;
        this.marcheJdbcRepository = marcheJdbcRepository;
    }

    @Override
    public MarcheDto getMarketById(Integer id) {
        MarcheEntity marcheEntity = marcheRepository.findById(id).orElseThrow(NotFoundExceptionMarche::new);
        return new MarcheDto(marcheEntity);
    }

    public List<MarcheDto> findAll() {
        return StreamSupport.stream(marcheRepository.findAll().spliterator(), false)
                .map(MarcheDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer integer) {
        List<MarcheDto> marcheDtoList = getAllMarket(false,false);
        boolean findMarket = marcheDtoList.stream()
                .anyMatch(marcheDto -> marcheDto.getId().equals(integer));
        if (!findMarket) {
            throw new NotFoundExceptionMarche();
        }
        marcheRepository.deleteById(integer);
    }

    public MarcheDto createMarket(MarcheDto marcheDto) {
        List<MarcheDto> marcheDtoList = getAllMarket(false,false);
        boolean nameExist = marcheDtoList.stream()
                .anyMatch(marcheDto1 -> marcheDto1.getNom().equals(marcheDto.getNom()));
        if (nameExist) {
            throw new BadRequestException();
        }
        return new MarcheDto(marcheRepository.save(marcheDto.market()));
    }

    @Override
    public List<MarcheDto> getAllMarket(Boolean marcheFutur, Boolean marcheAncien) {
        if (marcheFutur) {
            Date date = new Date();
            return marcheRepository.findByDateGreaterThan(date).stream()
                    .map(MarcheDto::new)
                    .collect(Collectors.toList());
        }
        if(marcheAncien) {
            Date date = new Date();
            return marcheRepository.findByDateLessThan(date).stream()
                    .map(MarcheDto::new)
                    .collect(Collectors.toList());
        }
        return StreamSupport.stream(marcheRepository.findAll().spliterator(), false)
                .map(MarcheDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public MarcheDto editMarket(MarcheDto marcheDto) {
        List<MarcheDto> marcheDtoList = getAllMarket(false,false);
        boolean findId = false;

        for (MarcheDto dto : marcheDtoList) {
            if (dto.getId().equals(marcheDto.getId())) {
                findId = true;
                break;
            }
        }
        if (!findId) {
            throw new NotFoundExceptionMarche();
        } else {
            MarcheDto marcheDto1 = new MarcheDto(marcheRepository.save(marcheDto.market()));
            marcheDto1.setId(marcheDto.getId());
            return marcheDto1;
        }
    }

    public List<EmplacementDto> getEmplacementMarche(Integer id, Boolean disponible) {
        MarcheEntity marcheEntity = marcheRepository.findById(id).orElseThrow(NotFoundExceptionMarche::new);
        if (disponible) {
            List<EmplacementEntity> emplacementEntityList = marcheJdbcRepository.getEmplacementsDisponible(marcheEntity.getId());
            return emplacementEntityList
                    .stream()
                    .map(EmplacementDto::new)
                    .collect(Collectors.toList());
        }
        List<EmplacementEntity> emplacementEntityList = marcheEntity.getEmplacements();
        return emplacementEntityList
                .stream()
                .map(EmplacementDto::new)
                .collect(Collectors.toList());
    }
}

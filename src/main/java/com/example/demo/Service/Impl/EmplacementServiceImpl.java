package com.example.demo.Service.Impl;

import com.example.demo.DAO.MarcheJdbcRepository;
import com.example.demo.Dto.EmplacementDto;
import com.example.demo.DO.EmplacementEntity;
import com.example.demo.DAO.EmplacementRepository;
import com.example.demo.Service.EmplacementService;
import com.example.demo.Status.NotFoundExceptionEmplacements;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmplacementServiceImpl implements EmplacementService {
    private EmplacementRepository emplacementRepository;

    public EmplacementServiceImpl(EmplacementRepository emplacementRepository, MarcheJdbcRepository marcheJdbcRepository) {
        this.emplacementRepository = emplacementRepository;
    }

    @Override
    public List<EmplacementDto> findAll() {
        return StreamSupport.stream(emplacementRepository.findAll().spliterator(),false)
                .map(EmplacementDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public EmplacementDto findById(Integer id) {
        EmplacementEntity emplacementEntity = emplacementRepository.findById(id).orElseThrow(NotFoundExceptionEmplacements::new);
        return new EmplacementDto(emplacementEntity);
    }

}

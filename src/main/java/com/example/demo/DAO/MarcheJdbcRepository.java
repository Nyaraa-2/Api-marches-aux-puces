package com.example.demo.DAO;

import com.example.demo.DO.EmplacementEntity;

import java.util.List;

public interface MarcheJdbcRepository {

    List<EmplacementEntity> getEmplacementsDisponible(Integer id);
}

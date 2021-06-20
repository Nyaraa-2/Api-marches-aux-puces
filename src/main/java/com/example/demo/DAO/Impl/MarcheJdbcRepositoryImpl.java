package com.example.demo.DAO.Impl;

import com.example.demo.DAO.MarcheJdbcRepository;
import com.example.demo.DO.EmplacementEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MarcheJdbcRepositoryImpl implements MarcheJdbcRepository {

    private JdbcTemplate jdbcTemplate;

    public MarcheJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmplacementEntity> getEmplacementsDisponible(Integer id) {
        return jdbcTemplate.query("SELECT emplacement.id, emplacement.numero, emplacement.numero_de_rue, emplacement.adresse, marche.nom " +
                "FROM emplacement_marche " +
                "JOIN emplacement ON emplacement_marche.id_emplacement = emplacement.id " +
                "JOIN marche ON emplacement_marche.id_marche = marche.id " +
                "WHERE marche.id = ? AND emplacement.id NOT IN (SELECT id_emplacement FROM reservation)",new BeanPropertyRowMapper<>(EmplacementEntity.class),id);
    }
}

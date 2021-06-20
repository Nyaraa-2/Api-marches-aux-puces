package com.example.demo.DAO.rowmapper;

import com.example.demo.DO.EmplacementEntity;
import com.example.demo.DO.MarcheEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcheEmplacementDisponibleRowMapper implements RowMapper<MarcheEntity> {
    @Override
    public MarcheEntity mapRow(ResultSet rs, int i) throws SQLException {
        MarcheEntity marcheEntity = new MarcheEntity();
        marcheEntity.setNom(rs.getString("marche.nom"));
        if(rs.getInt("emplacement.id") != 0){
            List<EmplacementEntity> emplacementEntityList = new ArrayList<>();
            for(EmplacementEntity e : emplacementEntityList){
                emplacementEntityList.add(new EmplacementEntity(rs.getInt("emplacement.id"),rs.getInt("emplacement.numero"),rs.getInt("emplacement.numero_de_rue"),rs.getString("emplacement.adresse")));
            }
            marcheEntity.setEmplacements(emplacementEntityList);
        }
        return marcheEntity;
    }
}


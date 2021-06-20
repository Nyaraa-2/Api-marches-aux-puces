package com.example.demo.DAO;

import com.example.demo.DO.MarcheEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

//methodeCustom
public interface MarcheRepository extends CrudRepository<MarcheEntity, Integer> {
    List<MarcheEntity> findByDateGreaterThan(Date date);
    List<MarcheEntity> findByDateLessThan(Date date);
}

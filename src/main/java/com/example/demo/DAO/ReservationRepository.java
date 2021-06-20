package com.example.demo.DAO;

import com.example.demo.DO.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {
    List<ReservationEntity> findByMarcheEntity_id(Integer id);

}

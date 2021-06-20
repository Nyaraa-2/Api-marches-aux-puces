package com.example.demo.DAO;
import com.example.demo.DO.ReservationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void findByMarcheEntity_id(){
        List<ReservationEntity> reservationEntities = reservationRepository.findByMarcheEntity_id(3);
        assertNotNull(reservationEntities);
        assertEquals(1,reservationEntities.size());
    }
}

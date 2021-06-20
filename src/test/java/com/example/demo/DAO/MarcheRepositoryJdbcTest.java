package com.example.demo.DAO;

import com.example.demo.DAO.Impl.MarcheJdbcRepositoryImpl;
import com.example.demo.DO.EmplacementEntity;
import com.example.demo.Dto.EmplacementDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
public class MarcheRepositoryJdbcTest {

    @Autowired
    MarcheJdbcRepositoryImpl marcheJdbcRepository;

    @Test
    public void getEmplacementsDisponible_ReturnListEmplacementDto(){
        List<EmplacementEntity> emplacementEntityList = marcheJdbcRepository.getEmplacementsDisponible(1);
        assertNotNull(emplacementEntityList);
        assertEquals(1,emplacementEntityList.get(0).getId());
        assertEquals("Avenue Jacques Prévert",emplacementEntityList.get(0).getAdresse());
        assertEquals(2,emplacementEntityList.get(1).getId());
        assertEquals(4186,emplacementEntityList.get(2).getNumero());
    }
}

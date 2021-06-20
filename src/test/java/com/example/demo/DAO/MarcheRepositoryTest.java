package com.example.demo.DAO;
import com.example.demo.DO.MarcheEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MarcheRepositoryTest {

    @Autowired
    MarcheRepository marcheRepository;

    @Test
    public void findByDateGreaterThan(){

        Date date = new Date();
        List<MarcheEntity> marcheEntities = marcheRepository.findByDateGreaterThan(date);
        assertNotNull(marcheEntities);
    }
}

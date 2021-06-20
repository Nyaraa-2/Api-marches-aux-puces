package com.example.demo.Service;
import com.example.demo.Dto.EmplacementDto;
import com.example.demo.DO.EmplacementEntity;
import com.example.demo.DAO.EmplacementRepository;
import com.example.demo.Service.Impl.EmplacementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class EmplacementServiceTest {
    @Mock
    private EmplacementRepository emplacementRepository;

    @InjectMocks
    private EmplacementServiceImpl emplacementServiceImpl;

    @BeforeEach
    public void init(){
        EmplacementEntity emplacementEntity = new EmplacementEntity();
        emplacementEntity.setId(1);
        emplacementEntity.setNumero(123);
        emplacementEntity.setAdresse("address");
        emplacementEntity.setNumero_de_rue(1);

        List<EmplacementEntity> emplacementEntityList = new ArrayList<>();
        emplacementEntityList.add(emplacementEntity);
        when(emplacementRepository.findAll()).thenReturn(emplacementEntityList);
        when (emplacementRepository.findById(1)).thenReturn(java.util.Optional.of(emplacementEntity));
    }

    @Test
    public void findAllEmplacement_ReturnDtoList(){
        List<EmplacementDto> emplacementDtoList = emplacementServiceImpl.findAll();
        assertNotNull(emplacementDtoList);
        assertEquals(1,emplacementDtoList.get(0).getId());
        verify(emplacementRepository,times(1)).findAll();
    }

    @Test
    public void findEmplacementById_ReturnEmplacementDto(){
        EmplacementDto emplacementDto = emplacementServiceImpl.findById(1);
        assertNotNull(emplacementDto);
        assertEquals(1,emplacementDto.getId());
        assertEquals("address",emplacementDto.getAdresse());
        assertEquals(123,emplacementDto.getNumero());
        assertEquals(1,emplacementDto.getNumero_de_rue());
        verify(emplacementRepository,times(1)).findById(1);
    }

}

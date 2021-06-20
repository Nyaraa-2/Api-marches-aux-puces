package com.example.demo.Controller;

import com.example.demo.Dto.EmplacementDto;
import com.example.demo.Service.EmplacementService;
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
public class EmplacementControllerTest {
    @Mock
    private EmplacementService emplacementService;
    @InjectMocks
    private EmplacementWsController emplacementWsController;


    @BeforeEach
    public void init(){
        EmplacementDto emplacementDto = new EmplacementDto();
        emplacementDto.setId(1);
        emplacementDto.setNumero(123);
        emplacementDto.setAdresse("address");
        emplacementDto.setNumero_de_rue(1);

        List<EmplacementDto> emplacementDtoList = new ArrayList<>();
        emplacementDtoList.add(emplacementDto);

        when(emplacementService.findById(1)).thenReturn(emplacementDto);
        when(emplacementService.findAll()).thenReturn(emplacementDtoList);
    }

    @Test
    public void getEmplacementById_ReturnEmplacement(){
        EmplacementDto emplacementDto = emplacementWsController.findEmplacements(1);
        assertNotNull(emplacementDto);
        assertEquals(1,emplacementDto.getId());
        assertEquals("address",emplacementDto.getAdresse());
        assertEquals(123,emplacementDto.getNumero());
        assertEquals(1,emplacementDto.getNumero_de_rue());
        verify(emplacementService,times(1)).findById(1);
    }

    @Test
    public void getListEmplacement_ReturnEmplacementList(){
        List<EmplacementDto> emplacementDtoList = emplacementWsController.findAllEmplacements();
        assertNotNull(emplacementDtoList);
        assertEquals(1,emplacementDtoList.get(0).getId());
        assertEquals("address",emplacementDtoList.get(0).getAdresse());
        assertEquals(123,emplacementDtoList.get(0).getNumero());
        assertEquals(1,emplacementDtoList.get(0).getNumero_de_rue());
        verify(emplacementService,times(1)).findAll();
    }
}

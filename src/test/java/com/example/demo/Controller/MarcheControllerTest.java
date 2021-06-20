package com.example.demo.Controller;
import com.example.demo.Dto.EmplacementDto;
import com.example.demo.Dto.MarcheDto;
import com.example.demo.Service.MarcheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MarcheControllerTest {
    @Mock
    MarcheService marcheService;

    @InjectMocks
    MarcheWSController marcheWSController;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    public void init() throws ParseException {

        EmplacementDto emplacementDto = new EmplacementDto();
        emplacementDto.setId(1);
        emplacementDto.setNumero(123);
        emplacementDto.setAdresse("address");
        emplacementDto.setNumero_de_rue(1);

        List<EmplacementDto> emplacementDtoList = new ArrayList<>();
        emplacementDtoList.add(emplacementDto);

        MarcheDto marcheDto = new MarcheDto();
        marcheDto.setId(1);
        marcheDto.setNom("Nom");
        marcheDto.setUrl_image("url");
        marcheDto.setEmplacements(emplacementDtoList);
        marcheDto.setDate(sdf.parse("2020-12-14"));

        MarcheDto marcheDto2 = new MarcheDto();
        marcheDto2.setId(2);
        marcheDto2.setNom("Nom");
        marcheDto2.setUrl_image("url");
        marcheDto2.setEmplacements(emplacementDtoList);
        marcheDto2.setDate(sdf.parse("2020-12-24"));

        List<MarcheDto> marcheDtoList = new ArrayList<>();
        marcheDtoList.add(marcheDto);
        marcheDtoList.add(marcheDto2);


        when(marcheService.getMarketById(1)).thenReturn(marcheDto);
        when(marcheService.createMarket(marcheDto)).thenReturn(marcheDto);
        when(marcheService.editMarket(marcheDto)).thenReturn(marcheDto);
        when(marcheService.getAllMarket(false,false)).thenReturn(marcheDtoList);
        when(marcheService.getEmplacementMarche(1,false)).thenReturn(emplacementDtoList);
        doNothing().when(marcheService).deleteById(1);
    }

    @Test
    public void getEmplacementByMarket_ReturnEmplacementList(){
        List<EmplacementDto> emplacementDtoList = marcheWSController.getEmplacementsByIdMarket(1,false);
        assertNotNull(emplacementDtoList);
        verify(marcheService,times(1)).getEmplacementMarche(1,false);
    }

    @Test
    public void deleteMarlet_DoNothing(){
        MarcheDto marcheDto = new MarcheDto();
        marcheDto.setId(1);
        marcheWSController.deleteMarket(1);
        verify(marcheService,times(1)).deleteById(1);
    }

    @Test
    public void getAllMarkets_ReturnMarkets() throws ParseException {
        List<MarcheDto> marcheDtoList = marcheWSController.getAllMarche(false,false);
        assertNotNull(marcheDtoList);
        assertEquals(1,marcheDtoList.get(0).getId());
        assertEquals(2,marcheDtoList.size());
        assertEquals(sdf.parse("2020-12-14"),marcheDtoList.get(0).getDate());
        verify(marcheService,times(1)).getAllMarket(false,false);
    }
    @Test
    public void getMarketByDate_ReturnFutursMarkets(){
        List<MarcheDto> marcheDtosList = marcheService.getAllMarket(true,false);
        assertNotNull(marcheDtosList);
    }

    @Test
    public void getMarketById_ReturnOneMarket() throws ParseException {
        MarcheDto marcheDto = marcheWSController.getMarketById(1);
        assertEquals(1,marcheDto.getId());
        assertEquals("Nom",marcheDto.getNom());
        assertEquals("url", marcheDto.getUrl_image());
        assertEquals(sdf.parse("2020-12-14"),marcheDto.getDate());
        assertNotNull(marcheDto);
        verify(marcheService,times(1)).getMarketById(1);
    }

    @Test
    public void createMarket_ReturnNewMarket() throws ParseException {
        EmplacementDto emplacementDto = new EmplacementDto();
        emplacementDto.setId(1);
        emplacementDto.setNumero(123);
        emplacementDto.setAdresse("address");
        emplacementDto.setNumero_de_rue(1);

        List<EmplacementDto> emplacementDtoList = new ArrayList<>();
        emplacementDtoList.add(emplacementDto);

        MarcheDto marcheDto = new MarcheDto();
        marcheDto.setId(1);
        marcheDto.setDate(sdf.parse("2020-12-14"));
        marcheDto.setNom("Nom");
        marcheDto.setUrl_image("URL");
        marcheDto.setEmplacements(emplacementDtoList);
        marcheWSController.createMarket(marcheDto);
        assertNotNull(marcheDto);
        assertEquals(123,marcheDto.getEmplacements().get(0).getNumero());
        assertEquals(1,marcheDto.getEmplacements().get(0).getNumero_de_rue());
        assertEquals(1,marcheDto.getEmplacements().get(0).getId());
        verify(marcheService,times(1)).createMarket(marcheDto);
    }

    @Test
    public void updateMarket_ReturnMarketUpdate() throws ParseException {
        EmplacementDto emplacementDto = new EmplacementDto();
        emplacementDto.setId(1);
        emplacementDto.setNumero(123);
        emplacementDto.setAdresse("address");
        emplacementDto.setNumero_de_rue(1);

        List<EmplacementDto> emplacementDtoList = new ArrayList<>();
        emplacementDtoList.add(emplacementDto);

        MarcheDto marcheDto = new MarcheDto();
        marcheDto.setId(1);
        marcheDto.setDate(sdf.parse("2020-12-14"));
        marcheDto.setNom("Nom");
        marcheDto.setUrl_image("URL");
        marcheDto.setEmplacements(emplacementDtoList);

        marcheWSController.editMarket(marcheDto.getId(),marcheDto);

        assertEquals(1,marcheDto.getId());
        assertEquals("Nom",marcheDto.getNom());
        assertEquals("URL", marcheDto.getUrl_image());
        assertNotNull(marcheDto);
        verify(marcheService,times(1)).editMarket(marcheDto);
    }

}

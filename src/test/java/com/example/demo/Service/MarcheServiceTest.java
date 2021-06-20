package com.example.demo.Service;
import com.example.demo.Dto.EmplacementDto;
import com.example.demo.Dto.MarcheDto;
import com.example.demo.DO.EmplacementEntity;
import com.example.demo.DO.MarcheEntity;
import com.example.demo.DAO.MarcheRepository;
import com.example.demo.Service.Impl.MarcheServiceImpl;
import com.example.demo.Status.BadRequestException;
import com.example.demo.Status.NotFoundExceptionMarche;
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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

public class MarcheServiceTest {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Mock
    private MarcheRepository marcheRepository;

    @InjectMocks
    private MarcheServiceImpl marcheServiceImpl;

    @BeforeEach
    public void init() throws ParseException {

        EmplacementEntity emplacementEntity = new EmplacementEntity();
        emplacementEntity.setId(1);
        emplacementEntity.setNumero(123);
        emplacementEntity.setAdresse("address");
        emplacementEntity.setNumero_de_rue(1);

        List<EmplacementEntity> emplacementEntityList = new ArrayList<>();
        emplacementEntityList.add(emplacementEntity);

        List<EmplacementEntity> emplacementEntityList1 = new ArrayList<>();

        MarcheEntity marcheEntity = new MarcheEntity();
        marcheEntity.setId(1);
        marcheEntity.setNom("Nom");
        marcheEntity.setUrl_image("url");
        marcheEntity.setEmplacements(emplacementEntityList);
        marcheEntity.setDate(sdf.parse("2020-12-08"));

        MarcheEntity marcheEntity1 = new MarcheEntity();
        marcheEntity1.setId(2);
        marcheEntity1.setNom("Nom");
        marcheEntity1.setUrl_image("url");
        marcheEntity1.setEmplacements(emplacementEntityList1);
        marcheEntity1.setDate(sdf.parse("2020-12-24"));

        List<MarcheEntity> marcheDtoList = new ArrayList<>();
        marcheDtoList.add(marcheEntity);
        marcheDtoList.add(marcheEntity1);

        List<MarcheEntity> marchesFuturs = new ArrayList<>();
        marchesFuturs.add(marcheEntity1);

        when(marcheRepository.findById(1)).thenReturn(java.util.Optional.of(marcheEntity));
        when(marcheRepository.findAll()).thenReturn(marcheDtoList);
        when(marcheRepository.findByDateGreaterThan(any(Date.class))).thenReturn(marchesFuturs);
        when(marcheRepository.save(any())).thenReturn(marcheEntity);
        doNothing().when(marcheRepository).deleteById(1);

    }

    @Test
    public void deleteMarketById_ReturnNothing(){
        MarcheDto marcheDto = new MarcheDto();
        marcheDto.setId(1);
        marcheServiceImpl.deleteById(1);
        verify(marcheRepository,times(1)).deleteById(1);
    }

    @Test
    public void deleteMarketById_Exception(){
        assertThrows(NotFoundExceptionMarche.class,()->marcheServiceImpl.deleteById(10));
    }

    @Test
    public void getEmplacementByMarket_ReturnListEmplacementsDtoByMarket(){
        List<EmplacementDto> emplacementDtoList = marcheServiceImpl.getEmplacementMarche(1,anyBoolean());
        assertNotNull(emplacementDtoList);
        assertEquals("address",emplacementDtoList.get(0).getAdresse());
        verify(marcheRepository,times(1)).findById(1);
    }

    @Test //test de l'erreur
    public void getEmplacementByMarket_Exception(){
        assertThrows(NotFoundExceptionMarche.class,()->marcheServiceImpl.getEmplacementMarche(10,anyBoolean()));
    }


    @Test
    public void createMarketEntity_ReturnMarketDto() throws ParseException {
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
        marcheDto.setNom("NomMarche");
        marcheDto.setUrl_image("URL");
        marcheDto.setEmplacements(emplacementDtoList);

        marcheServiceImpl.createMarket(marcheDto);
        assertNotNull(marcheDto);
        assertEquals(123,marcheDto.getEmplacements().get(0).getNumero());
        assertEquals(1,marcheDto.getEmplacements().get(0).getNumero_de_rue());
        assertEquals(1,marcheDto.getEmplacements().get(0).getId());
        verify(marcheRepository,times(1)).save(any());
    }


    @Test //test de l'erreur
    public void createMarket_ReturnException(){
        MarcheDto marcheDto = new MarcheDto();
        marcheDto.setId(1);
        marcheDto.setNom("Nom");
        assertThrows(BadRequestException.class,()->marcheServiceImpl.createMarket(marcheDto));
    }

    @Test
    public void editMarketEntity_ReturnMarketDto() throws ParseException {
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

        marcheServiceImpl.editMarket(marcheDto);
        assertNotNull(marcheDto);
        assertEquals(123,marcheDto.getEmplacements().get(0).getNumero());
        assertEquals(1,marcheDto.getEmplacements().get(0).getNumero_de_rue());
        assertEquals(1,marcheDto.getEmplacements().get(0).getId());
        verify(marcheRepository,times(1)).save(any());
    }


    @Test
    public void getMarketById_ReturnMarketDto(){
        MarcheDto marcheDto = marcheServiceImpl.getMarketById(1);
        assertNotNull(marcheDto);
        assertEquals(1,marcheDto.getId());
        assertEquals("Nom",marcheDto.getNom());
        assertEquals("url", marcheDto.getUrl_image());
        verify(marcheRepository,times(1)).findById(1);
    }

    @Test
    public void getAllMarketBooleanFalse_ReturnDtoList(){
        List<MarcheDto> marcheDtoList = marcheServiceImpl.getAllMarket(false,false);
        assertNotNull(marcheDtoList);
        assertEquals(2,marcheDtoList.size());
        verify(marcheRepository,times(1)).findAll();
    }

    @Test
    public void getAllMarketBooleanTrue_ReturnDtoList(){
        List<MarcheDto> marcheDtoList = marcheServiceImpl.getAllMarket(true,false);
        assertNotNull(marcheDtoList);
        assertEquals(1,marcheDtoList.size());
        verify(marcheRepository,times(1)).findByDateGreaterThan(any(Date.class));
    }
}

package com.example.demo.Service;
import com.example.demo.Dto.ReservationDto;
import com.example.demo.DO.EmplacementEntity;
import com.example.demo.DO.MarcheEntity;
import com.example.demo.DO.ReservationEntity;
import com.example.demo.DO.UtilisateurEntity;
import com.example.demo.DAO.ReservationRepository;
import com.example.demo.Service.Impl.ReservationServiceImpl;
import com.example.demo.Status.NotFoundExceptionMarche;
import com.example.demo.Status.NotFoundExceptionReserve;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ReservationServiceTest {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Mock
    private ReservationRepository reservationRepository;
    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;

    @BeforeEach
    public void init() throws ParseException {
        EmplacementEntity emplacementEntity = new EmplacementEntity();
        emplacementEntity.setId(1);
        emplacementEntity.setNumero(123);
        emplacementEntity.setAdresse("address");
        emplacementEntity.setNumero_de_rue(1);

        List<EmplacementEntity> emplacementEntityList = new ArrayList<>();
        emplacementEntityList.add(emplacementEntity);

        MarcheEntity marcheEntity = new MarcheEntity();
        marcheEntity.setId(1);
        marcheEntity.setNom("Nom");
        marcheEntity.setUrl_image("url");
        marcheEntity.setEmplacements(emplacementEntityList);
        marcheEntity.setDate(sdf.parse("2020-12-14"));

        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setId(1);
        utilisateurEntity.setNom("Nom");
        utilisateurEntity.setPrenom("prenom");
        utilisateurEntity.setNumero_de_rue(1);
        utilisateurEntity.setAdresse("addresseUtilisateur");
        utilisateurEntity.setEmail("mail@mail.com");
        utilisateurEntity.setTelephone("0601020304");


        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(1);
        reservationEntity.setNumero(5);
        reservationEntity.setDateReservation(sdf.parse("2020-12-20"));
        reservationEntity.setEmplacementEntity(emplacementEntity);
        reservationEntity.setUtilisateurEntity(utilisateurEntity);
        reservationEntity.setMarcheEntity(marcheEntity);

        List<ReservationEntity> reservationEntities = new ArrayList<>();
        reservationEntities.add(reservationEntity);

        when(reservationRepository.findById(1)).thenReturn(java.util.Optional.of(reservationEntity));
        when(reservationRepository.save(any())).thenReturn(reservationEntity);
        when(reservationRepository.findByMarcheEntity_id(1)).thenReturn(reservationEntities);
        when(reservationRepository.findAll()).thenReturn(reservationEntities);
        doNothing().when(reservationRepository).deleteById(1);
    }

    @Test
    public void deleteReservationById_ReturnNothing(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(1);
        reservationServiceImpl.deleteReservationDto(1);
        verify(reservationRepository,times(1)).deleteById(1);
    }

    @Test
    public void deleteReservationById_ReturnException(){
        assertThrows(NotFoundExceptionReserve.class,()->reservationServiceImpl.deleteReservationDto(18));
    }

    @Test
    public void findAllReservation_ReturnListDtoReservation(){
        List<ReservationDto> reservationDtos = reservationServiceImpl.getReservationByIdMarket(1);
        assertNotNull(reservationDtos);
        assertEquals(1,reservationDtos.get(0).getMarcheDto().getId());
        assertEquals("addresseUtilisateur",reservationDtos.get(0).getUtilisateurDto().getAdresse());
        verify(reservationRepository,times(1)).findByMarcheEntity_id(1);
    }

    @Test
    public void findReservationById_ReturnReservationDto() throws ParseException {

        ReservationDto reservationDto = reservationServiceImpl.getReservationById(1);

        assertEquals(1,reservationDto.getId());
        assertEquals("Nom",reservationDto.getUtilisateurDto().getNom());
        assertEquals(1,reservationDto.getMarcheDto().getId());
        assertEquals("address",reservationDto.getEmplacementDto().getAdresse());
        assertEquals(sdf.parse("2020-12-20"),reservationDto.getDate_reservation());
        verify(reservationRepository,times(1)).findById(1);
    }

    @Test
    public void findReservationById_ReturnException(){
        assertThrows(NotFoundExceptionMarche.class,()->reservationServiceImpl.getReservationById(18));
    }

    @Test
    public void updateReservation_ReturnReservationDtoUpdate() throws ParseException {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(1);
        reservationDto.setNumero(5);
        reservationDto.setDate_reservation(sdf.parse("2020-12-14"));

        reservationServiceImpl.editReservation(reservationDto);
        assertEquals(1,reservationDto.getId());
        assertEquals(5,reservationDto.getNumero());
        assertEquals(sdf.parse("2020-12-14"),reservationDto.getDate_reservation());
        assertNotNull(reservationDto);
        verify(reservationRepository,times(1)).save(any());
    }

    @Test
    public void createReservation_ReturnNewReservation() throws ParseException {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(1);
        reservationDto.setNumero(5);
        reservationDto.setDate_reservation(sdf.parse("2020-12-14"));

        reservationServiceImpl.createReservation(reservationDto);
        assertNotNull(reservationDto);
        assertEquals(1,reservationDto.getId());
        assertEquals(5,reservationDto.getNumero());
        assertEquals(sdf.parse("2020-12-14"),reservationDto.getDate_reservation());
    }
}

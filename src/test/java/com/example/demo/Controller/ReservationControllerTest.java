package com.example.demo.Controller;

import com.example.demo.Dto.EmplacementDto;
import com.example.demo.Dto.MarcheDto;
import com.example.demo.Dto.ReservationDto;
import com.example.demo.Dto.UtilisateurDto;
import com.example.demo.Service.ReservationService;
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
public class ReservationControllerTest {

    @Mock
    ReservationService reservationService;

    @InjectMocks
    ReservationWSController reservationWSController;

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

        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(1);
        utilisateurDto.setNom("Nom");
        utilisateurDto.setPrenom("prenom");
        utilisateurDto.setNumero_de_rue(1);
        utilisateurDto.setAdresse("addresseUtilisateur");
        utilisateurDto.setEmail("mail@mail.com");
        utilisateurDto.setTelephone("0601020304");


        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(1);
        reservationDto.setNumero(5);
        reservationDto.setDate_reservation(sdf.parse("2020-12-20"));
        reservationDto.setEmplacementDto(emplacementDto);
        reservationDto.setUtilisateurDto(utilisateurDto);
        reservationDto.setMarcheDto(marcheDto);

        List<ReservationDto> reservationDtos = new ArrayList<>();
        reservationDtos.add(reservationDto);

        when(reservationService.getReservationByIdMarket(1)).thenReturn(reservationDtos);
        when(reservationService.createReservation(reservationDto)).thenReturn(reservationDto);
        when(reservationService.editReservation(reservationDto)).thenReturn(reservationDto);
        when(reservationService.getReservationById(1)).thenReturn(reservationDto);
        doNothing().when(reservationService).deleteReservationDto(1);
    }

    @Test
    public void deleteReservationById_ReturnNothing(){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(1);
        reservationWSController.deleteReservatiobn(1);
        verify(reservationService,times(1)).deleteReservationDto(1);
    }

    @Test
    public void getReservationByMarket_ReturnListReservation(){
        List<ReservationDto> reservationDtos = reservationWSController.getReservation(1);
        assertNotNull(reservationDtos);
        assertEquals(1,reservationDtos.get(0).getMarcheDto().getId());
        assertEquals("addresseUtilisateur",reservationDtos.get(0).getUtilisateurDto().getAdresse());
        verify(reservationService,times(1)).getReservationByIdMarket(1);
    }

    @Test
    public void createReservation_ReturnNewReservation() throws ParseException {

        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(1);
        reservationDto.setNumero(5);
        reservationDto.setDate_reservation(sdf.parse("2020-12-14"));

        reservationWSController.createReservation(reservationDto);
        assertNotNull(reservationDto);
        assertEquals(1,reservationDto.getId());
        assertEquals(5,reservationDto.getNumero());
        assertEquals(sdf.parse("2020-12-14"),reservationDto.getDate_reservation());
        verify(reservationService,times(1)).createReservation(reservationDto);
    }

    @Test
    public void updateReservation_ReturnReservationUpdate() throws ParseException {

        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(1);
        reservationDto.setNumero(5);
        reservationDto.setDate_reservation(sdf.parse("2020-12-14"));

        reservationWSController.editReservation(reservationDto.getId(),reservationDto);
        assertEquals(1,reservationDto.getId());
        assertEquals(5,reservationDto.getNumero());
        assertEquals(sdf.parse("2020-12-14"),reservationDto.getDate_reservation());
        assertNotNull(reservationDto);
        verify(reservationService,times(1)).editReservation(reservationDto);
    }

    @Test
    public void getReservationById_ReturnReservation() throws ParseException {

        ReservationDto reservationDto = reservationWSController.getReservationById(1);

        assertEquals(1,reservationDto.getId());
        assertEquals("Nom",reservationDto.getUtilisateurDto().getNom());
        assertEquals(1,reservationDto.getMarcheDto().getId());
        assertEquals("address",reservationDto.getEmplacementDto().getAdresse());
        assertEquals(sdf.parse("2020-12-20"),reservationDto.getDate_reservation());
    }
}

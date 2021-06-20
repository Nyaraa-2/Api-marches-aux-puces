package com.example.demo.Controller;

import com.example.demo.Service.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UtilisateurControllerTest {
    @Mock
    private UtilisateurService utilisateurService;
    @InjectMocks
    private UtilisateurWsController utilisateurWsController;

    @BeforeEach
    public void init(){

    }
}

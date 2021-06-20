package com.example.demo.Controller;

import com.example.demo.Dto.UtilisateurDto;
import com.example.demo.Service.UtilisateurService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/utilisateur")
@Validated
@RestController
public class UtilisateurWsController {
    private UtilisateurService utilisateurService;

    public UtilisateurWsController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<UtilisateurDto> getAllUsers(){
        return utilisateurService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UtilisateurDto findUserById(@PathVariable Integer id){
        return utilisateurService.getUserById(id);
    }

    @PostMapping
    public UtilisateurDto addUser(@RequestBody UtilisateurDto utilisateurDto){
        return utilisateurService.addUser(utilisateurDto);
    }
}

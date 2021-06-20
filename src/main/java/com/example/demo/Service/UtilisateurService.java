package com.example.demo.Service;

import com.example.demo.Dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    List<UtilisateurDto> getAllUsers();
    UtilisateurDto getUserById(Integer id);
    UtilisateurDto addUser(UtilisateurDto utilisateurDto);
}

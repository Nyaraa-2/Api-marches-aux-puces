package com.example.demo.Service.Impl;

import com.example.demo.DAO.UtilisateurRepository;
import com.example.demo.DO.UtilisateurEntity;
import com.example.demo.Dto.UtilisateurDto;
import com.example.demo.Service.UtilisateurService;
import com.example.demo.Status.NotFoundExceptionUtilisateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public List<UtilisateurDto> getAllUsers() {
        return StreamSupport.stream(utilisateurRepository.findAll().spliterator(),false)
                .map(UtilisateurDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public UtilisateurDto getUserById(Integer id) {
        UtilisateurEntity utilisateurEntity = utilisateurRepository.findById(id).orElseThrow(NotFoundExceptionUtilisateur::new);
        return new UtilisateurDto(utilisateurEntity);
    }

    @Override
    public UtilisateurDto addUser(UtilisateurDto utilisateurDto) {
        return new UtilisateurDto(utilisateurRepository.save(utilisateurDto.toUtilisateur()));
    }
}

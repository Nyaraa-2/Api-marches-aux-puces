package com.example.demo.Controller;

import com.example.demo.Dto.EmplacementDto;
import com.example.demo.Service.EmplacementService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/emplacements")
@Validated
@RestController
public class EmplacementWsController  {
    private EmplacementService emplacementService;

    public EmplacementWsController(EmplacementService emplacementService) {
        this.emplacementService = emplacementService;
    }

    @GetMapping
    public List<EmplacementDto> findAllEmplacements(){
        return emplacementService.findAll();
    }

    @GetMapping("/{id}")
    public EmplacementDto findEmplacements(@PathVariable("id") Integer id){
        return emplacementService.findById(id);
    }
}

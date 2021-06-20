package com.example.demo.Controller;

import com.example.demo.Dto.EmplacementDto;
import com.example.demo.Dto.MarcheDto;
import com.example.demo.Service.MarcheService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/marches")
@Validated
@RestController
public class MarcheWSController {
    private MarcheService marcheService;

    public MarcheWSController(MarcheService marcheService) {
        this.marcheService = marcheService;
    }

    @GetMapping
    public List<MarcheDto> getAllMarche(@RequestParam(defaultValue = "false",required = false) Boolean marcheAVenir,@RequestParam(defaultValue = "false",required = false)Boolean marcheAncien){
        return marcheService.getAllMarket(marcheAVenir, marcheAncien);
    }

    @GetMapping("/{id}")
    public MarcheDto getMarketById(@PathVariable Integer id){
        return marcheService.getMarketById(id);
    }


    @DeleteMapping("/{id}")
    void deleteMarket(@PathVariable Integer id){
        marcheService.deleteById(id);
    }

    @PutMapping("/{id}")
    public MarcheDto editMarket(@PathVariable Integer id, @RequestBody MarcheDto marcheDto){
        marcheDto.setId(id);
        return marcheService.editMarket(marcheDto);
    }

    @PostMapping
    public MarcheDto createMarket(@Valid @RequestBody MarcheDto marcheDto){
        return marcheService.createMarket(marcheDto);
    }

    @GetMapping("/{id}/emplacements")
    public List<EmplacementDto> getEmplacementsByIdMarket(@PathVariable Integer id,@RequestParam(defaultValue = "false", required = false)Boolean disponible){
        if(disponible){
            return marcheService.getEmplacementMarche(id,true);
        }
        return marcheService.getEmplacementMarche(id,false);
    }
}

package com.caschile.horus.controller;

import com.caschile.horus.model.CajaDTO;
import com.caschile.horus.service.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="cajas")
@CrossOrigin
public class CajaController {

    @Autowired
    CajaService cajaService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{codigo}/{año}")
    public List<CajaDTO> getCaja(@PathVariable("codigo") int codigo, @PathVariable("año") int año){
        List<CajaDTO> caja = cajaService.getCaja(codigo, año);
        return caja;
    }
}

package com.caschile.horus.controller;

import com.caschile.horus.model.PermisoModel;
import com.caschile.horus.service.PermisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="permisos")
@CrossOrigin
public class PermisoController {

    @Autowired
    PermisoService permisoService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{caja}")
    public List<PermisoModel> getPermisos(@PathVariable("caja") short caja){
        List<PermisoModel> permisos = permisoService.getPermisos(caja);
        return permisos;
    }
}

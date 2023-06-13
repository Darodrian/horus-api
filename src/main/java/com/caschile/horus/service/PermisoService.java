package com.caschile.horus.service;

import com.caschile.horus.model.PermisoModel;
import com.caschile.horus.repository.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoService {

    @Autowired
    private PermisoRepository permisoRepo;

    public List<PermisoModel> getPermisos(Short caja) { return permisoRepo.findTop10ByNumeroCaja(caja); }
}

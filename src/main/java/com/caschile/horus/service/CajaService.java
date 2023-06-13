package com.caschile.horus.service;

import com.caschile.horus.dao.CajaDAO;
import com.caschile.horus.model.CajaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CajaService {

    @Autowired
    private CajaDAO cajaDAO;

    public List<CajaDTO> getCaja(Integer codigo, Integer año) { return cajaDAO.CajaAnualByCodigo(codigo, año); }
}

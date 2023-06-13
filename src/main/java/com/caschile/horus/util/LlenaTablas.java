package com.caschile.horus.util;

import com.caschile.horus.security.entity.Correlativo;
import com.caschile.horus.security.repository.CorrelativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LlenaTablas implements CommandLineRunner {

    @Autowired
    private CorrelativoRepository correlativoRepo;

    @Override
    public void run(String... args) throws Exception {
/**

 Correlativo correlativoRol = new Correlativo();
 Correlativo correlativoUsuario = new Correlativo();

 correlativoRol.setSequence_name("Rol");
 correlativoRol.setNext_val(0);
 correlativoRepo.save(correlativoRol);

 correlativoUsuario.setSequence_name("Usuario");
 correlativoUsuario.setNext_val(0);
 correlativoRepo.save(correlativoUsuario);
 */

    }
}

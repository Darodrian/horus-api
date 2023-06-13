package com.caschile.horus.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caschile.horus.security.entity.Usuario;
import com.caschile.horus.security.repository.UsuarioRepository;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String correo){
        return usuarioRepository.findByCorreo(correo);
    }





    public boolean existsByCorreo(String correo){
        return usuarioRepository.existsByCorreo(correo);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public Usuario findByCorreo( String correo) {
        Usuario u =usuarioRepository.findByCorreo(correo).get();
        System.out.println(u.getCorreo());
        return  u;
    }
}

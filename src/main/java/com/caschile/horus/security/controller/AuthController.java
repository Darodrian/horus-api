package com.caschile.horus.security.controller;

import com.caschile.horus.model.*;
import com.caschile.horus.util.Validaciones;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.caschile.horus.model.Mensaje;
import com.caschile.horus.security.dto.JwtDto;
import com.caschile.horus.security.dto.LoginUsuario;
import com.caschile.horus.security.dto.NuevoUsuario;
import com.caschile.horus.security.entity.Rol;
import com.caschile.horus.security.entity.Usuario;
import com.caschile.horus.security.enums.RolNombre;
import com.caschile.horus.security.jwt.JwtProvider;
import com.caschile.horus.security.service.RolService;
import com.caschile.horus.security.service.UsuarioService;


import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    Validaciones validaciones;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Validated @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        ResponseEntity<?> mensaje = null;
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Problemas con los campos enviados"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existsByCorreo(nuevoUsuario.getCorreo()))
            return new ResponseEntity(new Mensaje("El correo ya existe"), HttpStatus.BAD_REQUEST);

        if (!validaciones.validarEmail(nuevoUsuario.getCorreo())) {
            mensaje = new ResponseEntity(new Mensaje("El correo no es valido"), HttpStatus.BAD_REQUEST);
        } else if (!validaciones.validarRut(nuevoUsuario.getRut())) {
            mensaje = new ResponseEntity(new Mensaje("El rut no es valido"), HttpStatus.BAD_REQUEST);
        } else {
            Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getaPaterno(), nuevoUsuario.getaMaterno(), nuevoUsuario.getRut(),
                    nuevoUsuario.getCliente(), nuevoUsuario.getCelular(), nuevoUsuario.getCorreo(), passwordEncoder.encode(nuevoUsuario.getPassword()));
            Set<Rol> roles = new HashSet<>();
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
            if (nuevoUsuario.getRoles().contains("admin"))
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            usuario.setRoles(roles);
            usuarioService.save(usuario);
            mensaje = new ResponseEntity(new Mensaje("Se guardo el usuario"), HttpStatus.CREATED);
        }
        return mensaje;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        ResponseEntity<?> mensaje = null;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getCorreo(), loginUsuario.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
            mensaje = new ResponseEntity(jwtDto, HttpStatus.OK);
        } catch (Exception e) {
            mensaje = new ResponseEntity(new Mensaje("Usuario con problemas"), HttpStatus.BAD_REQUEST);
        }
        return mensaje;
    }

    private static final String VERIFICATION_TOKEN = "hola123";

    @GetMapping(value = "/webhooks")
    public ResponseEntity<Object> handleVerification(
            @RequestParam("hub.challenge") String challenge,
            @RequestParam("hub.verify_token") String verifyToken) {
        if (verifyToken.equals(VERIFICATION_TOKEN)) {
            System.out.println("Paso por aca");
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid verify token");
        }
    }

}

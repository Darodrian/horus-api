package com.caschile.horus.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.caschile.horus.model.BlackList;
import com.caschile.horus.security.entity.Usuario;
import com.caschile.horus.security.entity.UsuarioPrincipal;
import com.caschile.horus.security.service.UsuarioService;
import com.caschile.horus.service.BlackListService;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    @Autowired
    private BlackListService blackListService;

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    @Autowired
    UsuarioService usuarioService;
    SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Deprecated
    public String generateToken(Authentication authentication) {
        System.out.println(key);
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        String token = Jwts.builder().setSubject(usuarioPrincipal.getCorreo())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        Usuario user = usuarioService.getByNombreUsuario(usuarioPrincipal.getCorreo()).get();

        BlackList tkn = new BlackList();
        tkn.setToken(user.getToken());
        blackListService.guardarToken(tkn);

        user.setToken(token);
        usuarioService.save(user);
        System.out.println(new Date(new Date().getTime() + expiration * 1000));
        return token;
    }

    @Deprecated
    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    @Deprecated
    public boolean validateToken(String token) {
        System.out.println("este toqken: "+token);
        BlackList tkn = blackListService.getToken(token);
        try {
            if (tkn == null) {
                System.out.println("no se encontro token en lista negra");
                Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
                return true;
            }
        } catch (MalformedJwtException e) {
            logger.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("token vacío");
        } catch (SignatureException e) {
            System.out.println(e);
        }
        return false;
    }
}

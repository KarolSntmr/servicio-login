package com.shelter.controller;

import com.shelter.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private List<Usuario> usuariosRegistrados = new ArrayList<>();

    @PostMapping("/registro")
    public String registrar(@RequestBody Usuario usuario) {
        for (Usuario u : usuariosRegistrados) {
            if (u.getUsuario().equals(usuario.getUsuario())) {
                return "❌ Error: El usuario '" + usuario.getUsuario() + "' ya existe.";
            }
        }
        usuariosRegistrados.add(usuario);
        return "✅ Usuario '" + usuario.getUsuario() + "' registrado exitosamente.";
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        for (Usuario u : usuariosRegistrados) {
            if (u.getUsuario().equals(usuario.getUsuario())) {
                if (u.getContrasena().equals(usuario.getContrasena())) {
                    return "✅ Autenticación satisfactoria. ¡Bienvenido, " + usuario.getUsuario() + "!";
                } else {
                    return "❌ Error en la autenticación: Contraseña incorrecta.";
                }
            }
        }
        return "❌ Error en la autenticación: Usuario '" + usuario.getUsuario() + "' no encontrado.";
    }
}
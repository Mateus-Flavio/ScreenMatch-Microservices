package com.program.usuario_service.controller;

import com.program.usuario_service.model.Usuario;
import com.program.usuario_service.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilmeController {

    private final UsuarioRepository usuarioRepository;

    public FilmeController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/filmes")
    public String redirecionarParaFilmes(Authentication authentication) {

        String email = authentication.getName();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return "redirect:http://localhost:8082/filmes?usuarioId=" + usuario.getId();
    }
}
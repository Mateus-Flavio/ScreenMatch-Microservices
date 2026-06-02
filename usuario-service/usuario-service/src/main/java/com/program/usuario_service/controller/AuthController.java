package com.program.usuario_service.controller;

import com.program.usuario_service.model.Usuario;
import com.program.usuario_service.repository.UsuarioRepository;
import com.program.usuario_service.service.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository,
                          EmailService emailService,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String mostrarCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute Usuario usuario, Model model) {

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            model.addAttribute("error", "Este email já está cadastrado!");
            model.addAttribute("usuario", usuario);
            return "cadastro";
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);

        return "redirect:/login?cadastroSucesso";
    }

    @GetMapping("/esqueci-senha")
    public String esqueciSenha() {
        return "esqueci-senha";
    }

    @PostMapping("/esqueci-senha")
    public String enviarToken(@RequestParam String email) {

        usuarioRepository.findByEmail(email).ifPresent(usuario -> {

            String token = UUID.randomUUID().toString();
            usuario.setResetToken(token);
            usuarioRepository.save(usuario);

            String link = "http://localhost:8081/redefinir-senha?token=" + token;

            emailService.enviarEmail(
                    usuario.getEmail(),
                    "Redefinição de senha",
                    "Olá " + usuario.getNome() +
                            "\nClique no link para redefinir sua senha:\n" + link
            );
        });

        return "redirect:/login?emailEnviado";
    }

    @GetMapping("/redefinir-senha")
    public String mostrarRedefinirSenha(@RequestParam String token, Model model) {

        Usuario usuario = usuarioRepository.findByResetToken(token).orElse(null);

        if (usuario == null) {
            return "redirect:/login?tokenInvalido";
        }

        model.addAttribute("token", token);
        model.addAttribute("nomeUsuario", usuario.getNome());

        return "redefinir-senha";
    }

    @PostMapping("/redefinir-senha")
    public String redefinirSenha(@RequestParam String token,
                                 @RequestParam String senha) {

        Usuario usuario = usuarioRepository.findByResetToken(token).orElse(null);

        if (usuario == null) {
            return "redirect:/login?tokenInvalido";
        }

        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setResetToken(null);

        usuarioRepository.save(usuario);

        return "redirect:/login?senhaAlterada";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
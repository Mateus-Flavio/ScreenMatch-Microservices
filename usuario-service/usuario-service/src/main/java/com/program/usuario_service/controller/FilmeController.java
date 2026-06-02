package com.program.usuario_service.controller;


import com.program.usuario_service.client.FilmeClient;
import com.program.usuario_service.dto.FilmeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FilmeController {

    private final FilmeClient filmeClient;

    public FilmeController(FilmeClient filmeClient) {
        this.filmeClient = filmeClient;
    }

    @GetMapping("/filmes")
    public String listarFilmes(Model model) {
        List<FilmeDTO> filmes = filmeClient.listarFilmes();

        model.addAttribute("filmes", filmes);

        return "filmes";
    }


}
package com.screenmach.filmes_service.controller;

import com.screenmach.filmes_service.model.Filme;
import com.screenmach.filmes_service.service.FilmeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("filmes", service.listar());
        return "filmes";
    }

    @GetMapping("/novo")
    public String novoFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "form-filme";
    }

    @PostMapping("/salvar")
    public String salvarFormulario(@ModelAttribute Filme filme) {
        service.salvar(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Filme buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Filme> listarApi() {
        return service.listar();
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseBody
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
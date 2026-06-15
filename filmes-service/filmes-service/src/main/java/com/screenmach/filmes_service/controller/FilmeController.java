package com.screenmach.filmes_service.controller;

import com.screenmach.filmes_service.model.Filme;
import com.screenmach.filmes_service.service.FilmeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(@RequestParam Long usuarioId, Model model) {
        model.addAttribute("filmes", service.listarPorUsuario(usuarioId));
        model.addAttribute("usuarioId", usuarioId);
        return "filmes";
    }

    @GetMapping("/novo")
    public String novoFilme(@RequestParam Long usuarioId, Model model) {
        Filme filme = new Filme();
        filme.setUsuarioId(usuarioId);

        model.addAttribute("filme", filme);
        model.addAttribute("usuarioId", usuarioId);

        return "form-filme";
    }

    @PostMapping("/salvar")
    public String salvarFormulario(@ModelAttribute Filme filme) {
        service.salvar(filme);
        return "redirect:/filmes?usuarioId=" + filme.getUsuarioId();
    }

    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Filme buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam String titulo,
                         @RequestParam Long usuarioId,
                         Model model) {

        model.addAttribute("filmes", service.buscarPorTituloPorUsuario(titulo, usuarioId));
        model.addAttribute("usuarioId", usuarioId);

        return "filmes";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Filme> listarApi(@RequestParam Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, @RequestParam Long usuarioId) {
        service.deletar(id);
        return "redirect:/filmes?usuarioId=" + usuarioId;
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @RequestParam Long usuarioId, Model model) {
        Filme filme = service.buscarPorId(id);

        model.addAttribute("filme", filme);
        model.addAttribute("usuarioId", usuarioId);

        return "form-filme";
    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute Filme filme) {
        service.atualizar(filme);
        return "redirect:/filmes?usuarioId=" + filme.getUsuarioId();
    }
}
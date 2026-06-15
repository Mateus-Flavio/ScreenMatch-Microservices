package com.screenmach.filmes_service.service;

import com.screenmach.filmes_service.model.Filme;
import com.screenmach.filmes_service.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository repository;

    @Autowired
    private S3BackupService s3BackupService;

    public List<Filme> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public Filme salvar(Filme filme) {
        Filme filmeSalvo = repository.save(filme);
        s3BackupService.salvarBackupFilme(filmeSalvo);
        return filmeSalvo;
    }

    public Filme atualizar(Filme filme) {
        Filme filmeAtualizado = repository.save(filme);
        s3BackupService.salvarBackupFilme(filmeAtualizado);
        return filmeAtualizado;
    }

    public Filme buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));
    }

    public List<Filme> buscarPorTituloPorUsuario(String titulo, Long usuarioId) {
        return repository.findByTituloContainingIgnoreCaseAndUsuarioId(titulo, usuarioId);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
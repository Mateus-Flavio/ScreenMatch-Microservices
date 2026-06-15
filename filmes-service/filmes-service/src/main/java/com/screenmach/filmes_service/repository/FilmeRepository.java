package com.screenmach.filmes_service.repository;

import com.screenmach.filmes_service.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {



    List<Filme> findByTituloContainingIgnoreCase(String titulo);

    List<Filme> findByUsuarioId(Long usuarioId);

    List<Filme> findByTituloContainingIgnoreCaseAndUsuarioId(String titulo, Long usuarioId);
}
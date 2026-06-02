package com.screenmach.filmes_service.repository;

import com.screenmach.filmes_service.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme,Long> {


}

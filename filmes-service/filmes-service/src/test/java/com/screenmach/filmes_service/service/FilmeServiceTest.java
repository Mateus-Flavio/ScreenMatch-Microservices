package com.screenmach.filmes_service.service;

import com.screenmach.filmes_service.model.Filme;
import com.screenmach.filmes_service.repository.FilmeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmeServiceTest {

    @Mock
    private FilmeRepository repository;

    @Mock
    private S3BackupService s3BackupService;

    @InjectMocks
    private FilmeService service;

    @Test
    void deveListarFilmesPorUsuario() {
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("The Batman");
        filme.setUsuarioId(30L);

        when(repository.findByUsuarioId(30L)).thenReturn(List.of(filme));

        List<Filme> resultado = service.listarPorUsuario(30L);

        assertEquals(1, resultado.size());
        assertEquals("The Batman", resultado.get(0).getTitulo());
        assertEquals(30L, resultado.get(0).getUsuarioId());

        verify(repository).findByUsuarioId(30L);
    }

    @Test
    void deveSalvarFilmeEEnviarBackupS3() {
        Filme filme = new Filme();
        filme.setTitulo("Interestelar");
        filme.setGenero("Ficção");
        filme.setAno(2014);
        filme.setUsuarioId(30L);

        Filme filmeSalvo = new Filme();
        filmeSalvo.setId(1L);
        filmeSalvo.setTitulo("Interestelar");
        filmeSalvo.setGenero("Ficção");
        filmeSalvo.setAno(2014);
        filmeSalvo.setUsuarioId(30L);

        when(repository.save(filme)).thenReturn(filmeSalvo);

        Filme resultado = service.salvar(filme);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Interestelar", resultado.getTitulo());
        assertEquals(30L, resultado.getUsuarioId());

        verify(repository).save(filme);
        verify(s3BackupService).salvarBackupFilme(filmeSalvo);
    }

    @Test
    void deveBuscarFilmePorId() {
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Gladiador");

        when(repository.findById(1L)).thenReturn(Optional.of(filme));

        Filme resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Gladiador", resultado.getTitulo());

        verify(repository).findById(1L);
    }

    @Test
    void deveLancarErroQuandoFilmeNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException erro = assertThrows(RuntimeException.class, () -> {
            service.buscarPorId(99L);
        });

        assertEquals("Filme não encontrado", erro.getMessage());
    }
}
package com.program.usuario_service.service;

import com.program.usuario_service.model.Usuario;
import com.program.usuario_service.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveRetornarUserDetailsQuandoEmailExistir() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@email.com");
        usuario.setSenha("123456");

        when(usuarioRepository.findByEmailIgnoreCase("teste@email.com"))
                .thenReturn(Optional.of(usuario));

        UserDetails userDetails = usuarioService.loadUserByUsername(" teste@email.com ");

        assertEquals("teste@email.com", userDetails.getUsername());
        assertEquals("123456", userDetails.getPassword());

        verify(usuarioRepository).findByEmailIgnoreCase("teste@email.com");
    }

    @Test
    void deveLancarErroQuandoUsuarioNaoExistir() {
        when(usuarioRepository.findByEmailIgnoreCase("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->
                usuarioService.loadUserByUsername("naoexiste@email.com")
        );

        verify(usuarioRepository).findByEmailIgnoreCase("naoexiste@email.com");
    }
}
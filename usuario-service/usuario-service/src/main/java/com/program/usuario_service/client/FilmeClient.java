package com.program.usuario_service.client;

import com.program.usuario_service.dto.FilmeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FilmeClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public List<FilmeDTO> listarFilmes() {
        String url = "http://localhost:8082/filmes/api";

        FilmeDTO[] filmes = restTemplate.getForObject(url, FilmeDTO[].class);

        return Arrays.asList(filmes);
    }
}

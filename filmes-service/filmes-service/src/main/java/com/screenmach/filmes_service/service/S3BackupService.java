package com.screenmach.filmes_service.service;

import com.screenmach.filmes_service.model.Filme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.time.LocalDateTime;

@Service
public class S3BackupService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    public S3BackupService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void salvarBackupFilme(Filme filme) {
        String conteudoJson = """
                {
                  "id": "%s",
                  "titulo": "%s",
                  "genero": "%s",
                  "ano": "%s",
                  "descricao": "%s"
                }
                """.formatted(
                filme.getId(),
                filme.getTitulo(),
                filme.getGenero(),
                filme.getAno(),
                filme.getDescricao()
        );

        String nomeArquivo = "filmes/filme-" + filme.getId() + "-" + LocalDateTime.now() + ".json";

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(nomeArquivo)
                .contentType("application/json")
                .build();

        s3Client.putObject(request, RequestBody.fromString(conteudoJson));
    }
}
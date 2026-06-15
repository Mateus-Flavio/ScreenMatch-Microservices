package com.program.usuario_service.service;

import com.program.usuario_service.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.time.LocalDateTime;

@Service
public class S3UsuarioBackupService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    public S3UsuarioBackupService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void salvarBackupUsuario(Usuario usuario) {
        String conteudoJson = """
                {
                  "id": "%s",
                  "nome": "%s",
                  "email": "%s"
                }
                """.formatted(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );

        String nomeArquivo = "usuarios/usuario-" + usuario.getId() + "-" + LocalDateTime.now() + ".json";

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(nomeArquivo)
                .contentType("application/json")
                .build();

        s3Client.putObject(request, RequestBody.fromString(conteudoJson));
    }
}
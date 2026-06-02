package com.program.usuario_service.dto;

public class FilmeDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String genero;
    private Integer ano;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getAno() {
        return ano;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}

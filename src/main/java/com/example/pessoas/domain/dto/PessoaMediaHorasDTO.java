package com.example.pessoas.domain.dto;

import lombok.Data;

@Data
public class PessoaMediaHorasDTO {
    private String nomePessoa;
    private Double mediaHorasGastas;

    public PessoaMediaHorasDTO(String nomePessoa, Double mediaHorasGastas) {
        this.nomePessoa = nomePessoa;
        this.mediaHorasGastas = mediaHorasGastas;
    }
}

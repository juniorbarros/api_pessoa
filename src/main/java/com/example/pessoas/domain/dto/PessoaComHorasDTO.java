package com.example.pessoas.domain.dto;

import lombok.Data;

@Data
public class PessoaComHorasDTO {
    private String nomePessoa;
    private String nomeDepartamento;
    private Double totalHorasGastas;

    public PessoaComHorasDTO(String nomePessoa, String nomeDepartamento, Double totalHorasGastas) {
        this.nomePessoa = nomePessoa;
        this.nomeDepartamento = nomeDepartamento;
        this.totalHorasGastas = totalHorasGastas;
    }
}

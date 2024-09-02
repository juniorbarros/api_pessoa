package com.example.pessoas.domain.dto;

import lombok.Data;

@Data
public class DepartamentoInfoDTO {

    private String departamentoNome;
    private Long quantidadePessoas;
    private Long quantidadeTarefas;

    public DepartamentoInfoDTO(String departamentoNome, Long quantidadePessoas, Long quantidadeTarefas) {
        this.departamentoNome = departamentoNome;
        this.quantidadePessoas = quantidadePessoas;
        this.quantidadeTarefas = quantidadeTarefas;
    }
}

package com.example.pessoas.domain.dto;

import com.example.pessoas.domain.Departamento;
import com.example.pessoas.domain.Pessoa;
import com.example.pessoas.domain.Tarefa;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class TarefaDTO {
    private String titulo;
    private String descricao;
    private LocalDateTime prazo;
    private Double duracao;
    private Long pessoa_id;
    private Long departamento_id;
    private Departamento departamento;
    private Pessoa pessoa;

    public static TarefaDTO create(Tarefa pd) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pd,  TarefaDTO.class);

    }


}

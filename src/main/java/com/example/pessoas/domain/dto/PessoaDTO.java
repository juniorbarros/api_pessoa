package com.example.pessoas.domain.dto;

import com.example.pessoas.domain.Departamento;
import com.example.pessoas.domain.Pessoa;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class PessoaDTO {
    private Long id;
    private String nome;
    private Long Status;
    private Long departamento_id;
    private Departamento departamento;



    public static PessoaDTO create(Pessoa p) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p,  PessoaDTO.class);

    }
}

package com.example.pessoas.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Long status;


    @OneToOne
    @JoinColumn(name="departamento_id")
    private Departamento departamento;


    @OneToMany(mappedBy = "pessoa")
    private List<Tarefa> tarefas;

}

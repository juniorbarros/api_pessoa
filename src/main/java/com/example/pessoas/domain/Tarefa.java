package com.example.pessoas.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime prazo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    private Double duracao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private Boolean finalizado;
}




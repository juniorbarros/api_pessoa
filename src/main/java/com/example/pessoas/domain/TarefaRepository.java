package com.example.pessoas.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    @Query("SELECT p FROM Tarefa p WHERE p.departamento.id = :departamentoId")
    List<Pessoa> findByDepartamento(@Param("departamentoId") Long departamentoId);

    @Query("SELECT t FROM Tarefa t WHERE t.pessoa.id IS NULL ORDER BY t.prazo ASC LIMIT 3")
    List<Tarefa> findTop3ByPessoaAlocadaIsNullOrderByPrazoAsc();

}

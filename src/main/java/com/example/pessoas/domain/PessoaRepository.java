package com.example.pessoas.domain;

import com.example.pessoas.domain.dto.PessoaComHorasDTO;
import com.example.pessoas.domain.dto.PessoaMediaHorasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("SELECT p FROM Pessoa p WHERE p.departamento.id = :departamentoId")
    List<Pessoa> findByDepartamento(@Param("departamentoId") Long departamentoId);

    @Query("SELECT new com.example.pessoas.domain.dto.PessoaComHorasDTO(p.nome, d.descricao, SUM(t.duracao)) " +
            "FROM Pessoa p " +
            "JOIN p.departamento d " +
            "JOIN p.tarefas t " +
            "GROUP BY p.nome, d.descricao")
    List<PessoaComHorasDTO> listarPessoasComTotalHoras();

    @Query("SELECT new com.example.pessoas.domain.dto.PessoaMediaHorasDTO(p.nome, AVG(t.duracao)) " +
            "FROM Pessoa p " +
            "JOIN p.tarefas t " +
            "WHERE p.nome LIKE %:nome% " +
            "AND t.prazo BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY p.nome")
    List<PessoaMediaHorasDTO> buscarPessoasPorNomeEPeriodo(
            @Param("nome") String nome,
            @Param("dataInicio") LocalDateTime  dataInicio,
            @Param("dataFim") LocalDateTime dataFim
    );
}


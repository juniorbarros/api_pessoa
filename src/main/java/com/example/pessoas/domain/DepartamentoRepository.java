package com.example.pessoas.domain;

import com.example.pessoas.domain.dto.DepartamentoInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {


    @Query("SELECT new com.example.pessoas.domain.dto.DepartamentoInfoDTO(d.descricao, COUNT(DISTINCT p.id), COUNT(DISTINCT t.id)) " +
            "FROM Departamento d " +
            "LEFT JOIN Pessoa p ON (d.id = p.departamento.id)" +
            "LEFT JOIN Tarefa t ON(d.id = t.departamento.id) " +
            "GROUP BY d.descricao")
    List<DepartamentoInfoDTO> findDepartamentoInfo();
}

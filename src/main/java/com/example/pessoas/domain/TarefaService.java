package com.example.pessoas.domain;

import com.example.pessoas.domain.dto.TarefaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;


    public List<TarefaDTO> getTarefas() {
        return  tarefaRepository.findAll().stream()
                .map(TarefaDTO::create)
                .collect(Collectors.toList());
    }

    public Boolean update(Tarefa tarefa, Long id) {
        Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);
        if (optionalTarefa.isPresent()) {
            tarefaRepository.save(tarefa);
            return true;
        }
        return false;
    }

    public List<Tarefa> getTarefasPendentes() {
        return tarefaRepository.findTop3ByPessoaAlocadaIsNullOrderByPrazoAsc();
    }
}

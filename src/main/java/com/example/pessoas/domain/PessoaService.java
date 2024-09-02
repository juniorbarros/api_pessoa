package com.example.pessoas.domain;

import com.example.pessoas.domain.dto.PessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository rep;

    public List<PessoaDTO> getPessoas() {

        return rep.findAll().stream()
                .map(PessoaDTO::create)
                .collect(Collectors.toList());
    }

    public Optional<PessoaDTO> getPessoaById(long id) {
        return rep.findById(id).map(PessoaDTO::create);
    }


    public List<PessoaDTO> getPessoaByDepartamento(Long departamento) {
        return rep.findByDepartamento(departamento).stream()
                .map(PessoaDTO::create)
                .collect(Collectors.toList());
    }

    public Pessoa save(Pessoa pessoa) {

        return rep.save(pessoa);

    }

    public Boolean delete(Long id) {

        if(getPessoaById(id).isPresent()) {
            rep.deleteById(id);
            return true;
        }
        return false;
    }

    public Boolean update(Pessoa pessoa, long id) {


        Optional<Pessoa> optionalPessoa = rep.findById(id);
        if(optionalPessoa.isPresent()) {
            rep.save(pessoa);

            return true;
        }
        return null;
    }
}

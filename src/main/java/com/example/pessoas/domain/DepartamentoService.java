package com.example.pessoas.domain;

import com.example.pessoas.domain.dto.DepartamentoInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;


    public List<DepartamentoInfoDTO> getDepartamentoInfo() {
        return departamentoRepository.findDepartamentoInfo();
    }
}

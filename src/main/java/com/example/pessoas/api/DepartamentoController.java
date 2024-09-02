package com.example.pessoas.api;

import com.example.pessoas.domain.DepartamentoService;
import com.example.pessoas.domain.dto.DepartamentoInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public List<DepartamentoInfoDTO> listarDepartamentoInfo() {
        return departamentoService.getDepartamentoInfo();
    }


}

package com.example.pessoas.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String get(){
        return "get";
    }



    @PostMapping("/pessoa")
    public String postPessoas(){
        return "Adicionar um pessoa";
    }

    @PutMapping("/pessoas/{id}")
    public String putPessoas(@PathVariable("id") int id){
        return "Atualizar um pessoa";
    }

    @DeleteMapping("/pessoas/{id}")
    public String deletePessoas(@PathVariable("id") int id){
        return "Remover pessoa";
    }

    @PostMapping("tarefas")
    public String postTarefas(){
        return "Adicionar uma tarefa";
    }

    @PutMapping("taferas/alocar/{id}")
    public String putTarefas(@PathVariable("id") int id){
        return "Alocar uma pessoa na tarefa que tenha o mesmo departamento ";
    }

    @PutMapping("/tarefas/finalizar/{id}")
    public String putFinalizarTarefa(@PathVariable("id") int id){
        return "Finalizar uma pessoa na tarefa";
    }

    @GetMapping("pessoas/gastos")
    public String getPessoaGastos(){
        return "Buscar pessoas por nome e período, retorna média de horas gastas por tarefa";
    }

    @GetMapping("tarefas/pendentes")
    public String getPessoaGastosPendentes(){
        return "Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos";
    }

    @GetMapping("departamentos")
    public String getPessoaGastosDepartamentos(){
        return "Listar departamento e quantidade de pessoas e tarefas";
    }
}

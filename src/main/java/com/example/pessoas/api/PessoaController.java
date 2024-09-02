package com.example.pessoas.api;

import com.example.pessoas.domain.*;
import com.example.pessoas.domain.dto.PessoaComHorasDTO;
import com.example.pessoas.domain.dto.PessoaDTO;
import com.example.pessoas.domain.dto.PessoaMediaHorasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService service;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping()
    public List<PessoaComHorasDTO> listarPessoasComHoras() {
        return pessoaRepository.listarPessoasComTotalHoras();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPessoa(@PathVariable("id") long id){
        Optional<PessoaDTO> pessoa = service.getPessoaById(id);

        return pessoa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/departamento/{id}")
    public ResponseEntity getPessoaDepartamento(@PathVariable("id") Long id){
        List<PessoaDTO> pessoas = service.getPessoaByDepartamento(id);

        return pessoas.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(pessoas);
    }



    @GetMapping("/gastos")
    public List<PessoaMediaHorasDTO> buscarPessoasPorNomeEPeriodo(
            @RequestParam String nome,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date  dataFim) {

        return pessoaRepository.buscarPessoasPorNomeEPeriodo(nome, convertedata(dataInicio), convertedata(dataFim));
    }

    @PostMapping
    public ResponseEntity<Pessoa> postPessoa(@RequestBody PessoaDTO pessoa){
       try{
           Pessoa p = new Pessoa();

           if(pessoa.getNome() == null || pessoa.getNome().isEmpty()){
               return ResponseEntity.badRequest().build();
           }

           p.setNome(pessoa.getNome());
           p.setStatus(pessoa.getStatus());

           Optional<Departamento> departamento = departamentoRepository.findById(pessoa.getDepartamento_id());

           if(departamento.isPresent()){
               Departamento departamentox = departamento.get();
               p.setDepartamento(departamentox);
           } else {
               return ResponseEntity.badRequest().build();
           }


           Pessoa savePessoa = pessoaRepository.save(p);
            URI localtion = getUri(pessoa.getId());
           return ResponseEntity.created(localtion).build();
       } catch (Exception e) {
           return ResponseEntity.badRequest().build();
       }

    }
    private LocalDateTime convertedata(Date dtx){
        LocalDateTime localDateTime = convertDateToLocalDateTime(dtx);
        return localDateTime;
    }

    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePessoa(@PathVariable("id") long id){
        Boolean x = service.delete(id);
        if(x){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putPessoa(@PathVariable("id") long id, @RequestBody PessoaDTO pessoa){

        try{
            Pessoa p = new Pessoa();

            p.setId(id);
            p.setNome(pessoa.getNome());
            p.setStatus(pessoa.getStatus());


            Departamento departamento = departamentoRepository.findById(pessoa.getDepartamento_id())
                    .orElseThrow(() -> new RuntimeException("Departamento id not found"));

            p.setDepartamento(departamento);

            Boolean savePessoa = service.update(p, id);

            return savePessoa ?
                    ResponseEntity.created(null).build() :
                    ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

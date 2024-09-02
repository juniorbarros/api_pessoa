package com.example.pessoas.api;


import com.example.pessoas.domain.*;
import com.example.pessoas.domain.dto.TarefaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.DocFlavor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping()
    public ResponseEntity<List<TarefaDTO>> getTarefas(){
        return ResponseEntity.ok(tarefaService.getTarefas());
    }

    @PostMapping()
    public ResponseEntity<String> postTarefa(@RequestBody TarefaDTO request){

        try{
            if((request.getTitulo() == null || request.getTitulo().isEmpty())
            || (request.getDescricao() == null || request.getDescricao().isEmpty())
            || (request.getDepartamento_id() == null) )
            {
                return ResponseEntity.badRequest().build();
            }

            Tarefa tf = new Tarefa();

            tf.setTitulo(request.getTitulo());
            tf.setDescricao(request.getDescricao());
            tf.setPrazo(LocalDateTime.now());
            tf.setDuracao(request.getDuracao());

            if(request.getDepartamento_id() != null){
                Departamento departamento = departamentoRepository.findById(request.getDepartamento_id())
                        .orElseThrow(() -> new RuntimeException("Departamento id not found"));

                tf.setDepartamento(departamento);
            }
            if(request.getPessoa_id() != null){
                Pessoa pessoa = pessoaRepository.findById(request.getPessoa_id())
                        .orElseThrow(() -> new RuntimeException("pesso id not found"));

                tf.setPessoa(pessoa);
            }


            Tarefa saveTarefa = tarefaRepository.save(tf);
            URI localtion = getUri(saveTarefa.getId());
            return ResponseEntity.created(localtion).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }


    }

    @PutMapping("/alocar/{id}")
    public ResponseEntity putAlocarTarefa(@PathVariable Long id, @RequestBody TarefaDTO request){

        if(id == null || request.getPessoa_id() == null){
            return ResponseEntity.badRequest().build();
        }

        Optional<Tarefa> tarefaOptional  = tarefaRepository.findById(id);
        Optional<Pessoa> pessoaOptional  = pessoaRepository.findById(request.getPessoa_id());

        if(tarefaOptional.isPresent() && pessoaOptional.isPresent()){
            Tarefa tarefa = tarefaOptional.get();
            Pessoa pessoa = pessoaOptional.get();

            if(pessoa.getDepartamento().getId().equals(tarefa.getDepartamento().getId())){
                tarefa.setPessoa(pessoa);
                tarefa.setPrazo(LocalDateTime.now());
                tarefaRepository.save(tarefa);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/finalizar/{id}")
    public ResponseEntity finalizarTarefa(@PathVariable Long id){
        if(id == null){
            return ResponseEntity.badRequest().build();
        }

        Optional<Tarefa> tarefaOptional  = tarefaRepository.findById(id);


        if(tarefaOptional.isPresent()){
            Tarefa tf = tarefaOptional.get();

            LocalDateTime dt = tf.getPrazo();
            LocalDateTime dx = LocalDateTime.now();
            tf.setId(id);
            tf.setDuracao(CalculoTempoGasto(dt, dx));
            tf.setFinalizado(true);

            tarefaRepository.save(tf);
            return ResponseEntity.ok().build();
        }
        return null;
    }


    @GetMapping("/pendentes")
    public List<Tarefa> listarTarefasPendentes() {
        return tarefaService.getTarefasPendentes();
    }


    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    public Double CalculoTempoGasto (LocalDateTime d1, LocalDateTime d2){

            Duration duracao = Duration.between(d1, d2);

            long minutus = duracao.toMinutes();

            return formata(minutus);

    }

    private Double formata(long valor){
        int vlr = (int) valor;
        int horas = vlr/ 60;
        int minutos = vlr % 60;

        String horaMinutoFormatado = String.valueOf(horas)+'.'+String.valueOf(minutos);

        return Double.parseDouble(horaMinutoFormatado);
    }
}


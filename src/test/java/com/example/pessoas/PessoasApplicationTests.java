package com.example.pessoas;

import com.example.pessoas.domain.*;
import com.example.pessoas.domain.dto.PessoaDTO;
import com.example.pessoas.domain.dto.TarefaDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PessoasApplicationTests {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaService tarefaService;


    @Test
    public void testPessoa() {
        PessoaDTO pessoa = new PessoaDTO();
        pessoa.setNome("pessoa teste");
        pessoa.setDepartamento_id(5L);
        pessoa.setStatus(1L);

        Pessoa p = new Pessoa();

        p.setNome(pessoa.getNome());
        p.setStatus(pessoa.getStatus());

        Optional<Departamento> departamento = departamentoRepository.findById(pessoa.getDepartamento_id());

        assertTrue(departamento.isPresent());

        Departamento departamentox = departamento.get();
        p.setDepartamento(departamentox);


        Pessoa pp = pessoaService.save(p);

        assertNotNull(pp);

        Long id_p = pp.getId();
        assertNotNull(id_p);

        // busca pessoa
        Optional<Pessoa> pessoa2 = pessoaRepository.findById(id_p);
        assertTrue(pessoa2.isPresent());

        pp = pessoa2.get();
        assertEquals("pessoa teste", pp.getNome());
        assertEquals(5L, pp.getDepartamento().getId());
        assertEquals(1L, pp.getStatus());


        // cria uma tarefa e aloca uma pessoa

        TarefaDTO tarefa = new TarefaDTO();
        tarefa.setTitulo("valor 22");
        tarefa.setDescricao("preço maior que a margem");
        tarefa.setDuracao(0D);
        tarefa.setFinalizado(false);
        tarefa.setDepartamento_id(5L);

        Tarefa ts = new Tarefa();
        ts.setTitulo(tarefa.getTitulo());
        ts.setDescricao(tarefa.getDescricao());
        ts.setDuracao(tarefa.getDuracao());
        ts.setFinalizado(tarefa.getFinalizado());

        Optional<Departamento> departamentoT = departamentoRepository.findById(tarefa.getDepartamento_id());
        assertTrue(departamentoT.isPresent());


        Tarefa saveTarefa = tarefaRepository.save(ts);
        assertNotNull(saveTarefa);

        Long id_t = saveTarefa.getId();
        assertNotNull(id_t);

        // busca pessoa
        Optional<Tarefa> pf = tarefaRepository.findById(id_t);
        assertTrue(pf.isPresent());

        ts = pf.get();
        assertEquals("valor 22", ts.getTitulo());
        assertEquals("preço maior que a margem", ts.getDescricao());
        assertEquals(0, ts.getDuracao());
        assertEquals(false, ts.getFinalizado());


        // alocar pessoa
        Optional<Tarefa> tarefaOptional  = tarefaRepository.findById(id_t);
        assertTrue(tarefaOptional.isPresent());
        Optional<Pessoa> pessoaOptional  = pessoaRepository.findById(id_p);
        assertTrue(pessoaOptional.isPresent());

        Tarefa tarefa1 = tarefaOptional.get();
        Pessoa pessoa1 = pessoaOptional.get();


        tarefa1.setPessoa(pessoa1);
        tarefa1.setPrazo(LocalDateTime.now());
        tarefaRepository.save(tarefa1);

        Optional<Tarefa> pfa = tarefaRepository.findById(id_t);
        Tarefa tfaO = tarefaOptional.get();
        assertEquals(id_p, pfa.get().getPessoa().getId());

        LocalDateTime dt = tfaO.getPrazo();
        LocalDateTime dx = LocalDateTime.now();
        tfaO.setId(id_t);
        tfaO.setDuracao(CalculoTempoGasto(dt, dx));
        tfaO.setFinalizado(true);

        tarefaRepository.save(tfaO);

        Optional<Tarefa> pfaf = tarefaRepository.findById(id_t);
        assertTrue(pfaf.isPresent());

        assertEquals(tfaO.getFinalizado(), pfaf.get().getFinalizado());
        // finalizar tarefa


        // deleta o objeto
        tarefaService.delete(id_t);

        // verifica se deletado
        assertFalse(tarefaRepository.findById(id_t).isPresent());

        // deleta o objeto pessoa
        pessoaService.delete(id_p);

        // verifica se deletado
        assertFalse(pessoaRepository.findById(id_p).isPresent());




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

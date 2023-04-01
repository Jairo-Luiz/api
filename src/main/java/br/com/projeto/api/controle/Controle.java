package br.com.projeto.api.controle;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.modelo.Cliente;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;
import br.com.projeto.api.servico.Servico;

@RestController
@RequestMapping(value = "/pessoas")
public class Controle {

    @Autowired
    private Repositorio repositorio;

    @Autowired
    private Servico servico;

    @GetMapping
    public ResponseEntity<?> listarPessoas(){
        return servico.selecionar();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo){
        return servico.selecionarPeloCodigo(codigo);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPessoa(@RequestBody Pessoa pessoa){
        return servico.cadastrar(pessoa);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody Pessoa pessoa){
        return servico.editar(pessoa);
    }

    
    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo){
        return servico.remover(codigo);
    }


    @GetMapping("/contador")
    public long contador(){
        return repositorio.count();
    }

    @GetMapping("/ordenarNomes")
    public List<Pessoa> ordenarNomes(){
        return repositorio.findByOrderByNome();
    }

    @GetMapping("/ordenarNomes2")
    public List<Pessoa> ordenarNomes2(){
        return repositorio.findByNomeOrderByIdadeDesc("Jairo");
    }
    
    @GetMapping("/nomeContem")
    public List<Pessoa> nomeContem(){
        return repositorio.findByNomeContaining("a");
    }

    @GetMapping("/nomeComeca")
    public List<Pessoa> nomeComeca(){
        return repositorio.findByNomeStartsWith("a");
    }

    @GetMapping("/nomeTermina")
    public List<Pessoa> nomeTermina(){
        return repositorio.findByNomeEndsWith("a");
    }

    @GetMapping("/somaIdades")
    public int somaIdades(){
        return repositorio.somaIdades();
    }

    @GetMapping("/idade")
    public List<Pessoa> idadeMaiorIgual(){
        return repositorio.idadeMaiorIgual(35);
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente cliente){

    }

}

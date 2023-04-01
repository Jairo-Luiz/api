package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.modelo.Mensagem;
import br.com.projeto.api.modelo.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@Service
public class Servico {
    
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio repositorio;

//metodo para criar
    public ResponseEntity<?> cadastrar(Pessoa pessoa){
        if(pessoa.getNome().equals("")){
            mensagem.setMensagem("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else if(pessoa.getIdade() < 0){
            mensagem.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repositorio.save(pessoa), HttpStatus.CREATED);
        }
    }

//metodo para selecionar
    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(repositorio.findAll(), HttpStatus.OK);
    }

//metodo para selecionar por codigo
    public ResponseEntity<?> selecionarPeloCodigo(int codigo){
        if(repositorio.countByCodigo(codigo) == 0){
            mensagem.setMensagem("Não foi encontrada nenhuma pessoa.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repositorio.findByCodigo(codigo), HttpStatus.OK);
        }
    }

//metodo para editar dados
    public ResponseEntity<?> editar(Pessoa pessoa){
        if(repositorio.countByCodigo(pessoa.getCodigo()) == 0){
            mensagem.setMensagem("O código informado não existe.");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if(pessoa.getNome().equals("")){
            mensagem.setMensagem("É necessário informar um nome.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if(pessoa.getIdade() < 0){
            mensagem.setMensagem("Informe uma idade válida.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity<>(repositorio.save(pessoa), HttpStatus.OK);
        }
    }

//metodo para remover registros
    public ResponseEntity<?> remover(int codigo){
        if(repositorio.countByCodigo(codigo) == 0){
            mensagem.setMensagem("O código informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else{
            Pessoa pessoa = repositorio.findByCodigo(codigo);
            repositorio.delete(pessoa);

            mensagem.setMensagem("Pessoa removida com sucesso!");

            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }

}

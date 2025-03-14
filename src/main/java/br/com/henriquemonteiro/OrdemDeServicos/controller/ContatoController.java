package br.com.henriquemonteiro.OrdemDeServicos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.henriquemonteiro.OrdemDeServicos.model.Contato;
import br.com.henriquemonteiro.OrdemDeServicos.repository.ContatoRepository;
@RestController
@RequestMapping("/contatos")


public class ContatoController {
	
	@Autowired 
	private ContatoRepository contatoRepository;
	
	@GetMapping//método utilizado para listar os contatos
	public List<Contato> listarContatos(){
		
		return contatoRepository.findAll();
	}
	
	
	@PostMapping//método para cadastrar novo contato
	public ResponseEntity<Contato> cadastrarContato(@RequestBody Contato contato){
		Contato novoContato = contatoRepository.save(contato);
		return new ResponseEntity<>(novoContato, HttpStatus.CREATED);
		}

	@PutMapping("/{id}")//método para atualizar o contato já existente
	public ResponseEntity<Contato> alterarContato(@PathVariable Long id, @RequestBody Contato contato) {
		if (!contatoRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		contato.setId(id);
		Contato atualizado = contatoRepository.save(contato);
		return new ResponseEntity<>(atualizado, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")//Método para excluir um contato existente
	public ResponseEntity<Void> excluirContato(@PathVariable Long id) {
		if (!contatoRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		contatoRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}




	

	}
	



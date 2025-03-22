package br.com.henriquemonteiro.ordemdeservicos.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.henriquemonteiro.ordemdeservicos.dto.EnderecoUpdateDTO;
import br.com.henriquemonteiro.ordemdeservicos.model.Endereco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import br.com.henriquemonteiro.ordemdeservicos.model.Contato;
import br.com.henriquemonteiro.ordemdeservicos.repository.ContatoRepository;
import br.com.henriquemonteiro.ordemdeservicos.dto.ContatoUptadeDTO;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

	@Autowired
	private ContatoRepository contatoRepository;

	// Endpoint público (sem validação de scopes)
	@GetMapping()
	@Operation(summary = "Listar todos os contatos")
	public List<Contato> listarContatos() {
		return contatoRepository.findAll();
	}

	// Endpoint protegido: exige o scope "create:contatos"

	@PostMapping()
	@Operation(summary = "Cadastrar novo contato")
	@PreAuthorize("hasAuthority('SCOPE_create:contatos')")
	public ResponseEntity<Contato> cadastrarContato(@Valid @RequestBody Contato contato) {
		if (contato.getEnderecos() != null) {
			contato.getEnderecos().forEach(endereco -> endereco.setContato(contato));
		}
		Contato novoContato = contatoRepository.save(contato);
		return new ResponseEntity<>(novoContato, HttpStatus.CREATED);
	}

	// Endpoint protegido: exige o scope "update:contatos"
	@PutMapping()
	@Operation(summary = "Alterar contato existente")
	@PreAuthorize("hasAuthority('SCOPE_update:contatos')")
	public ResponseEntity<Contato> alterarContato(@PathVariable UUID id, @RequestBody Contato contato) {
		if (!contatoRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		contato.setId(id);
		Contato atualizado = contatoRepository.save(contato);
		return new ResponseEntity<>(atualizado, HttpStatus.OK);
	}

	// Endpoint protegido: exige o scope "delete:contatos"
	@DeleteMapping()
	@Operation(summary = "Excluir contato existente")
	@PreAuthorize("hasAuthority('SCOPE_delete:contatos')")
	public ResponseEntity<Void> excluirContato(@PathVariable UUID id) {
		if (!contatoRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		contatoRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Endpoint protegido: exige o scope "update:contatos"
	@PatchMapping()
	@Transactional
	@PreAuthorize("hasAuthority('SCOPE_update:contatos')")
	@Operation(
			summary = "Atualiza Parcialmente um Contato",
			description = "Atualiza apenas os campos fornecidos. Campos não fornecidos permanecem inalterados."
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Contato não encontrado")
	})
	public ResponseEntity<Contato> atualizarParcialmenteContato(@PathVariable UUID id, @RequestBody ContatoUptadeDTO updateDTO) {
		Optional<Contato> contatoOptional = contatoRepository.findById(id);
		if (contatoOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Contato contato = contatoOptional.get();

		if (updateDTO.getNome() != null) {
			contato.setNome(updateDTO.getNome());
		}
		if (updateDTO.getEmail() != null) {
			contato.setEmail(updateDTO.getEmail());
		}
		if (updateDTO.getTelefone() != null) {
			contato.setTelefone(updateDTO.getTelefone());
		}
		if (updateDTO.getDataNascimento() != null) {
			contato.setDataNascimento(updateDTO.getDataNascimento());
		}

		if (updateDTO.getEnderecos() != null) {
			for (EnderecoUpdateDTO enderecoUpdateDTO : updateDTO.getEnderecos()) {
				Optional<Endereco> enderecoOptional = contato.getEnderecos().stream()
						.filter(e -> e.getId().equals(enderecoUpdateDTO.getId()))
						.findFirst();

				if (enderecoOptional.isPresent()) {
					Endereco endereco = enderecoOptional.get();
					if (enderecoUpdateDTO.getRua() != null) {
						endereco.setRua(enderecoUpdateDTO.getRua());
					}
					if (enderecoUpdateDTO.getNumero() != null) {
						endereco.setNumero(enderecoUpdateDTO.getNumero());
					}
					if (enderecoUpdateDTO.getCep() != null) {
						endereco.setCep(enderecoUpdateDTO.getCep());
					}
				}
			}
		}

		Contato contatoAtualizado = contatoRepository.save(contato);
		return new ResponseEntity<>(contatoAtualizado, HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


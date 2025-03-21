package br.com.henriquemonteiro.ordemdeservicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquemonteiro.ordemdeservicos.model.Endereco;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID>{

}

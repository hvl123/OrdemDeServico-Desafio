package br.com.henriquemonteiro.ordemdeservicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquemonteiro.ordemdeservicos.model.Contato;

import java.util.UUID;

public interface ContatoRepository extends JpaRepository <Contato, UUID>{

	
}

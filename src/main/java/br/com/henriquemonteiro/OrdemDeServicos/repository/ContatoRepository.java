package br.com.henriquemonteiro.OrdemDeServicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquemonteiro.OrdemDeServicos.model.Contato;

public interface ContatoRepository extends JpaRepository <Contato, Long>{

	
}

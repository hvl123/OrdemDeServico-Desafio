package br.com.henriquemonteiro.OrdemDeServicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.henriquemonteiro.OrdemDeServicos.model.Endereco;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID>{

}

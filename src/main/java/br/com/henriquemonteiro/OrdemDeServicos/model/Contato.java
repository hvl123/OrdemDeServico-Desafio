package br.com.henriquemonteiro.OrdemDeServicos.model;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class Contato {

	    @Id
	    private Long id;
	    private String nome;
	    private String email;
	    private String telefone;
	    private LocalDate dataNascimento;
	    
	    @OneToMany
	    private List<Endereco> enderecos;
	
	    
	    

}

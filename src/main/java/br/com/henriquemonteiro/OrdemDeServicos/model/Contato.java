package br.com.henriquemonteiro.OrdemDeServicos.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Contato {

	    @Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Schema(hidden = true)// oculta o campo de id no swagger, já que o campo id será gerado automáticamente
	    private UUID id;
	    private String nome;
	    private String email;
	    private String telefone;
	    private LocalDate dataNascimento;

	@OneToMany(mappedBy = "contato", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Endereco> enderecos;
	@JsonManagedReference// evita loop infinito indicando que essa é a classe pai.
	@Schema(hidden = true)
	public void addEndereco(Endereco endereco) {
		if (enderecos == null) {
			enderecos = new ArrayList<>();
		}
		enderecos.add(endereco);
		endereco.setContato(this);
	}
}


	
	    
	    



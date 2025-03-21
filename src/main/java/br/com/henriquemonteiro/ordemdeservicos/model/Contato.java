package br.com.henriquemonteiro.ordemdeservicos.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@Entity
@Data
public class Contato {

	    @Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Schema(hidden = true)// oculta o campo de id no swagger, já que o campo id será gerado automáticamente
	    private UUID id;

		@NotBlank(message = "O campo nome é obrigatório")
		@Size(min = 3, max = 50, message = "O nome deve contar no mínimo 3 caracteres")
	    private String nome;

		@NotBlank(message = "O campo email é obrigatório")
		@Email(message = "Favor inserir um formato de email válido")
	    private String email;

		@NotBlank(message = "O número de telefone é obrigatório")
	    private String telefone;

		@NotNull(message = "A data de nascimento é obrigatória")
//		@Past(message =  "Favor inserir uma data de nascimento válida")
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


	
	    
	    



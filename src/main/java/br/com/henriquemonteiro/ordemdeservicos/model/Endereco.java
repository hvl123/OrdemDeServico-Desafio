package br.com.henriquemonteiro.ordemdeservicos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;




@Entity
@Data
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(hidden = true)
	private UUID id;

	@NotBlank(message = "O campo nome é obrigatório")
	@Size(min = 3, max = 50, message = "O nome deve contar no mínimo 3 caracteres")
	private String rua;

	@NotBlank(message = "O campo nome é obrigatório")
	@Size(min = 3, max = 50, message = "O nome deve contar no mínimo 3 caracteres")
	private String numero;

	@NotBlank(message = "O CEP é obrigatório")
	@Pattern(regexp = "^\\d{5}-\\d{3}$", message = "Formato de CEP inválido. Exemplo: 12345-678")
	private String cep;


	@ManyToOne
	@JoinColumn(name = "contato_id")
	@JsonBackReference // Indica que este é o lado "filho" do relacionamento
	@Schema(hidden = true) // Oculta o campo "contato" no swagger.
	private Contato contato;
	
	

}

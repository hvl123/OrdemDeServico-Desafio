package br.com.henriquemonteiro.OrdemDeServicos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;




@Entity
@Data
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(hidden = true)
	private UUID id;
	private String rua;
	private String numero;
	private String cep;


	@ManyToOne
	@JoinColumn(name = "contato_id")
	@JsonBackReference // Indica que este é o lado "filho" do relacionamento
	@Schema(hidden = true) // Oculta o campo "contato" no swagger.
	private Contato contato;
	
	

}

package br.com.henriquemonteiro.OrdemDeServicos.dto;

import lombok.Data;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

@Data
public class EnderecoUpdateDTO {

    @Schema(description = "ID do endereço a ser atualizado", example = "223e4567-e89b-12d3-a456-426614174001")
    private UUID id;

    @Schema(description = "Nova rua do endereço (opcional)", example = "Rua das Margaridas")
    private String rua;

    @Schema(description = "Novo número do endereço (opcional)", example = "1500")
    private String numero;

    @Schema(description = "Novo CEP do endereço (opcional)", example = "01234-567")
    private String cep;



}

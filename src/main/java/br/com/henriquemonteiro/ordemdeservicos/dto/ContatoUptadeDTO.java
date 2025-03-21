package br.com.henriquemonteiro.ordemdeservicos.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ContatoUptadeDTO {

        @Schema(description = "Novo nome do contato (opcional)")
        private String nome;

        @Schema(description = "Novo email do contato (opcional)")
        private String email;

        @Schema(description = "Novo telefone do contato (opcional)")
        private String telefone;

        @Schema(description = "Nova data de nascimento do contato (opcional)")
        private LocalDate dataNascimento;

        @Schema(description = "Lista de endereços para atualização (opcional)")
        private List<EnderecoUpdateDTO> enderecos;
}

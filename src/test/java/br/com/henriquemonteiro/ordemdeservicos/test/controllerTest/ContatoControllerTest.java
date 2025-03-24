package br.com.henriquemonteiro.ordemdeservicos.test.controllerTest;


import br.com.henriquemonteiro.ordemdeservicos.controller.ContatoController;
import br.com.henriquemonteiro.ordemdeservicos.model.Contato;
import br.com.henriquemonteiro.ordemdeservicos.repository.ContatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class ContatoControllerTest {

    @Mock
    private ContatoRepository contatoRepository;

    @InjectMocks
    private ContatoController contatoController;

    private Contato contato;
    private UUID contatoId;

    @BeforeEach
    void setUp() {
        contatoId = UUID.randomUUID();
        contato = new Contato();
        contato.setId(contatoId);
        contato.setNome("Henrique Monteiro");
        contato.setEmail("henrique@email.com");
    }

    @Test
    void listarContatos_DeveRetornarListaVazia_QuandoNaoExistemContatos() {
        when(contatoRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(contatoController.listarContatos().isEmpty());
    }

    @Test
    void cadastrarContato_DeveSalvarContatoERetornarCreated() {
        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);
        ResponseEntity<Contato> response = contatoController.cadastrarContato(contato);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(contato, response.getBody());
    }

    @Test
    void alterarContato_DeveAtualizarContatoExistente() {
        // Criando um contato existente simulado
        Contato contatoExistente = new Contato();
        contatoExistente.setId(contatoId);
        contatoExistente.setNome("Nome Antigo");
        contatoExistente.setEmail("antigo@email.com");
        contatoExistente.setTelefone("123456789");
        contatoExistente.setDataNascimento(LocalDate.of(1990, 1, 1));

        // Criando um contato atualizado simulado
        Contato contatoAtualizado = new Contato();
        contatoAtualizado.setId(contatoId);
        contatoAtualizado.setNome("Nome Novo");
        contatoAtualizado.setEmail("novo@email.com");
        contatoAtualizado.setTelefone("987654321");
        contatoAtualizado.setDataNascimento(LocalDate.of(1995, 5, 5));

        when(contatoRepository.existsById(contatoId)).thenReturn(true);
        when(contatoRepository.findById(contatoId)).thenReturn(Optional.of(contatoExistente));
        when(contatoRepository.save(any(Contato.class))).thenReturn(contatoAtualizado);

        ResponseEntity<Contato> response = contatoController.alterarContato(contatoId, contatoAtualizado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contatoAtualizado.getNome(), response.getBody().getNome());
        assertEquals(contatoAtualizado.getEmail(), response.getBody().getEmail());
        assertEquals(contatoAtualizado.getTelefone(), response.getBody().getTelefone());
        assertEquals(contatoAtualizado.getDataNascimento(), response.getBody().getDataNascimento());
    }
    @Test
    void excluirContato_DeveRemoverContato() {
        when(contatoRepository.existsById(contatoId)).thenReturn(true);
        doNothing().when(contatoRepository).deleteById(contatoId);

        ResponseEntity<Void> response = contatoController.excluirContato(contatoId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

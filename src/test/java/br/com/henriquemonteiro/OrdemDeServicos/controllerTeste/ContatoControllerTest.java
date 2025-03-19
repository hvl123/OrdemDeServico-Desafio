//package br.com.henriquemonteiro.OrdemDeServicos.controllerTeste;
//
//
//import br.com.henriquemonteiro.OrdemDeServicos.controller.ContatoController;
//import br.com.henriquemonteiro.OrdemDeServicos.model.Contato;
//import br.com.henriquemonteiro.OrdemDeServicos.repository.ContatoRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Collections;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//@MockitoSettings(strictness = Strictness.LENIENT)
//@ExtendWith(MockitoExtension.class)
//class ContatoControllerTest {
//
//    @Mock
//    private ContatoRepository contatoRepository;
//
//    @InjectMocks
//    private ContatoController contatoController;
//
//    private Contato contato;
//    private UUID contatoId;
//
//    @BeforeEach
//    void setUp() {
//        contatoId = UUID.randomUUID();
//        contato = new Contato();
//        contato.setId(contatoId);
//        contato.setNome("Henrique Monteiro");
//        contato.setEmail("henrique@email.com");
//    }
//
//    @Test
//    void listarContatos_DeveRetornarListaVazia_QuandoNaoExistemContatos() {
//        when(contatoRepository.findAll()).thenReturn(Collections.emptyList());
//        assertTrue(contatoController.listarContatos().isEmpty());
//    }
//
//    @Test
//    void cadastrarContato_DeveSalvarContatoERetornarCreated() {
//        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);
//        ResponseEntity<Contato> response = contatoController.cadastrarContato(contato);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(contato, response.getBody());
//    }
//
//    @Test
//    void alterarContato_DeveAtualizarContatoExistente() {
//        when(contatoRepository.existsById(contatoId)).thenReturn(true);
//        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);
//
//        ResponseEntity<Contato> response = contatoController.alterarContato(contatoId, contato);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(contato, response.getBody());
//    }
//
//    @Test
//    void excluirContato_DeveRemoverContato() {
//        when(contatoRepository.existsById(contatoId)).thenReturn(true);
//        doNothing().when(contatoRepository).deleteById(contatoId);
//
//        ResponseEntity<Void> response = contatoController.excluirContato(contatoId);
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//    }
//}

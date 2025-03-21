package br.com.henriquemonteiro.ordemdeservicos.test.modelTest;

import br.com.henriquemonteiro.ordemdeservicos.model.Contato;
import br.com.henriquemonteiro.ordemdeservicos.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnderecoTest {

    @InjectMocks
    private Endereco endereco;

    @Mock
    private Contato contatoMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    public void testarGettersESettersDoEndereco() {
        // Configuração dos valores
        UUID id = UUID.randomUUID();
        String rua = "Rua das Flores";
        String numero = "123";
        String cep = "12345-678";

        // Atribuição dos valores
        endereco.setId(id);
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setCep(cep);
        endereco.setContato(contatoMock);

        // Verificações
        assertEquals(id, endereco.getId());
        assertEquals(rua, endereco.getRua());
        assertEquals(numero, endereco.getNumero());
        assertEquals(cep, endereco.getCep());
        assertEquals(contatoMock, endereco.getContato());
    }

    @Test
    public void testarMetodoToStringDoEndereco() {

        UUID id = UUID.randomUUID();
        String rua = "Avenida Paulista";
        String numero = "1000";
        String cep = "01310-100";


        endereco.setId(id);
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setCep(cep);
        endereco.setContato(null); // Garantir que o contato seja null

        // Verifica o método toString
        String expectedToString = "Endereco(id=" + id + ", rua=" + rua + ", numero=" + numero +
                ", cep=" + cep + ", contato=null)";
        assertEquals(expectedToString, endereco.toString());
    }

    @Test
    public void testarRelacionamentoComContato() {

        UUID contatoId = UUID.randomUUID();
        when(contatoMock.getId()).thenReturn(contatoId);


        endereco.setContato(contatoMock);


        assertNotNull(endereco.getContato());
        assertEquals(contatoMock, endereco.getContato());

        // Chama o método getId do contato para garantir que ele seja invocado
        UUID idRetornado = endereco.getContato().getId();

        // Verifica se o método getId foi chamado no contato
        verify(contatoMock, times(1)).getId();
        assertEquals(contatoId, idRetornado); // Verifica se o ID retornado é o esperado
    }
}
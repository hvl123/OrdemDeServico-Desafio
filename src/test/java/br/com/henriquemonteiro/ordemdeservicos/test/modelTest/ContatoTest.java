package br.com.henriquemonteiro.ordemdeservicos.test.modelTest;

import br.com.henriquemonteiro.ordemdeservicos.model.Contato;
import br.com.henriquemonteiro.ordemdeservicos.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContatoTest {

    @InjectMocks
    private Contato contato;

    @Mock
    private Endereco enderecoMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    public void testarGettersESettersDoContato() {
        // Configuração dos valores
        UUID id = UUID.randomUUID();
        String nome = "João Silva";
        String email = "joao.silva@example.com";
        String telefone = "11999999999";
        LocalDate dataNascimento = LocalDate.of(1990, 1, 1);

        // Atribuição dos valores
        contato.setId(id);
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setTelefone(telefone);
        contato.setDataNascimento(dataNascimento);

        // Verificações
        assertEquals(id, contato.getId());
        assertEquals(nome, contato.getNome());
        assertEquals(email, contato.getEmail());
        assertEquals(telefone, contato.getTelefone());
        assertEquals(dataNascimento, contato.getDataNascimento());
    }

    @Test
    public void testarAdicionarEndereco() {
        // Configuração do mock
        when(enderecoMock.getContato()).thenReturn(contato);

        // Adiciona o endereço ao contato
        contato.addEndereco(enderecoMock);

        // Verifica se o endereço foi adicionado
        assertNotNull(contato.getEnderecos());
        assertEquals(1, contato.getEnderecos().size());
        assertEquals(enderecoMock, contato.getEnderecos().get(0));

        // Verifica se o método setContato foi chamado no endereço
        verify(enderecoMock, times(1)).setContato(contato);
    }

    @Test
    public void testarAdicionarEnderecoQuandoListaEstaNula() {
        // Configura a lista de endereços como null
        contato.setEnderecos(null);

        // Adiciona o endereço ao contato
        contato.addEndereco(enderecoMock);

        // Verifica se a lista foi inicializada
        assertNotNull(contato.getEnderecos());
        assertEquals(1, contato.getEnderecos().size());
        assertEquals(enderecoMock, contato.getEnderecos().get(0));
    }

    @Test
    public void testarMetodoToStringDoContato() {
        // Configuração dos valores
        UUID id = UUID.randomUUID();
        String nome = "Maria Oliveira";
        String email = "maria.oliveira@example.com";
        String telefone = "11988888888";
        LocalDate dataNascimento = LocalDate.of(1985, 5, 15);

        // Atribuição dos valores
        contato.setId(id);
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setTelefone(telefone);
        contato.setDataNascimento(dataNascimento);

        // Verifica o método toString
        String expectedToString = "Contato(id=" + id + ", nome=" + nome + ", email=" + email +
                ", telefone=" + telefone + ", dataNascimento=" + dataNascimento + ", enderecos=null)";
        assertEquals(expectedToString, contato.toString());
    }
}
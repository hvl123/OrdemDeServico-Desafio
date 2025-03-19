package br.com.henriquemonteiro.OrdemDeServicos.controllerTeste;


import br.com.henriquemonteiro.OrdemDeServicos.controller.AuthController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {
        "auth0.issuer=https://auth0.com/",
        "auth0.clientId=client-id",
        "auth0.clientSecret=client-secret",
        "auth0.audience=audience"
})
class AuthControllerTest {

    @Mock
    private RestTemplate restTemplate; // Mocking RestTemplate

    @InjectMocks
    private AuthController authController; // Injecting the mock into the AuthController

    private String tokenUrl;

    @BeforeEach
    void setUp() {
        // As propriedades são injetadas automaticamente pelo Spring durante o setup
        tokenUrl = authController.getIssuer() + "oauth/token"; // Usando um getter para acessar o issuer
    }

//    @Test
//    void getToken_DeveRetornarToken_QuandoAPICallForBemSucedida() {
//        // Criando uma resposta simulada
//        Map<String, String> responseBody = new HashMap<>();
//        responseBody.put("access_token", "fake-access-token");
//
//        // Definindo o ResponseEntity com status 200 OK
//        ResponseEntity<Map> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
//
//        // Simulando a chamada REST com o RestTemplate
//        when(restTemplate.exchange(eq(tokenUrl), eq(HttpMethod.POST), any(HttpEntity.class), eq(Map.class)))
//                .thenReturn(responseEntity);
//
//        // Chamando o método getToken
//        ResponseEntity<?> response = authController.getToken();
//
//        // Adicionando logs para inspecionar a resposta
//        System.out.println("Status code da resposta: " + response.getStatusCode());
//        System.out.println("Corpo da resposta: " + response.getBody());
//
//        // Verificando se o código de status da resposta é OK
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        // Verificando se o corpo da resposta contém o token
//        assertNotNull(response.getBody());
//        assertTrue(((Map<String, String>) response.getBody()).containsKey("access_token"));
//    }



    @Test
    void getToken_DeveRetornarErro_QuandoAPICallFalhar() {
        // Simulando uma falha na chamada REST
        when(restTemplate.exchange(eq(tokenUrl), eq(HttpMethod.POST), any(HttpEntity.class), eq(Map.class)))
                .thenThrow(new RuntimeException("Erro ao obter o token"));

        // Chamando o método getToken
        ResponseEntity<?> response = authController.getToken();

        // Verificando se o código de status é INTERNAL_SERVER_ERROR
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        // Verificando se a resposta contém a chave de erro
        assertTrue(((Map) response.getBody()).containsKey("error"));
        assertTrue(((Map) response.getBody()).containsKey("message"));
    }
}

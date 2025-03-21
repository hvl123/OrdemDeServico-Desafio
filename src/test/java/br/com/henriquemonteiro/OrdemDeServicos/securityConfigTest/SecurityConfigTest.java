package br.com.henriquemonteiro.OrdemDeServicos.securityConfigTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    // 1. Endpoints públicos devem ser acessíveis sem autenticação
    @Test
    void quandoAcessarSwagger_DevePermitirAcesso() throws Exception {
        mockMvc.perform(get("/swagger-ui/index.html"))
                .andExpect(status().isOk());
    }

    @Test
    void quandoAcessarTokenEndpoint_DevePermitirAcesso() throws Exception {
        mockMvc.perform(post("/api/token"))
                .andExpect(status().isOk());
    }

    // 2. Acesso ao endpoint de contatos COM autenticação e permissão correta
    @Test
    @WithMockUser(authorities = "SCOPE_read:contatos") // Simula usuário com permissão correta
    void quandoAcessarContatosComPermissao_DevePermitirAcesso() throws Exception {
        mockMvc.perform(get("/api/contatos"))
                .andExpect(status().isOk());
    }

    // 3. Acesso ao endpoint de contatos SEM autenticação
    @Test
    @WithAnonymousUser
    void quandoAcessarContatosSemAutenticacao_DeveNegarAcesso() throws Exception {
        mockMvc.perform(get("/api/contatos"))
                .andExpect(status().isUnauthorized());
    }

    // 4. Acesso ao endpoint de contatos COM autenticação mas SEM permissão correta
    @Test
    @WithMockUser(authorities = "SCOPE_wrong_scope") // Simula usuário com permissão errada
    void quandoAcessarContatosSemPermissaoAdequada_DeveNegarAcesso() throws Exception {
        mockMvc.perform(get("/api/contatos"))
                .andExpect(status().isForbidden());
    }
}
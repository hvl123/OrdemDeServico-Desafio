# OrdemDeServico-Desafio
Exercitando conceitos de CRUD em uma aplicação de cadastro e endereços

API para gerenciamento de contatos e endereços com autenticação JWT via Auth0. Desenvolvida em **Java 23**, **Spring Boot 3.4.5**, **PostgreSQL** e documentada com **Swagger**.

---

## 🌟 Funcionalidades
- ✅ Cadastro de contatos e endereços
- 🔐 Autenticação JWT via Auth0
- 📄 Documentação interativa com Swagger


---

## ⚙️ Pré-requisitos
- **JDK 21+** (recomendado [Amazon Corretto 21](https://aws.amazon.com/pt/corretto/))
- **Maven 3.9+**
- **PostgreSQL 15+**
- **Conta no [Auth0](https://auth0.com/)** (para tokens JWT)

---

## 🛠️ Configuração Inicial

### 1. Clone o repositório
git clone https://github.com/hvl123/OrdemDeServico-Desafio/tree/main

### 2. Configure o projeto

utilize o comando: maven clean install 
o maven baixará as dependências necessárias 

### 3. Crie e configure o banco na aplicação
(sugestão de banco Postgres)
-- Execute no PostgreSQL
CREATE DATABASE ordemdeservico;
CREATE USER api_user WITH PASSWORD 'senha_segura';
GRANT ALL PRIVILEGES ON DATABASE ordemdeservico TO api_user;

### 4. Crie uma conta no auth0
O projeto utiliza autenticação então é necessário criar uma conta e configurar
o acesso.
crie uma api que tenha acesso as seguintes scopes:
read:contatos, delete:contatos, uptade:contatos e create:contatos;
Lembrando que os endpoints do programa só funcionam com um token com essas permissões

## 5. Configure o properties
 --- Exemplo de config ----


Spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_seu_banco

spring.datasource.username=seu_user
spring.datasource.password=sua_senha

auth0.audience=seu_auth0_audience
auth0.issuer=seu_auth0_issuer (**Lembrar de no final da url colocar "/" para funcionar corretamente)
auth0.clientId=seu_clientID
auth0.clientSecret=seu_client_secret

## 6. Teste a aplicação

Acesse o Swagger UI após iniciar a aplicação:
🔗 http://localhost:8080/swagger-ui.html

gere o token no endpoint post/token

faça o login com o token gerado na parte superior da página

## 7. Dificuldades ou dúvidas

entre em contato com:
henriquehvl1@gmail.com
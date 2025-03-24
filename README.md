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

utilize o comando: mvn clean install -DskipTests
(o comando -DskipTest fara o maven compilar pulando os testes unitarios para compilar pela primeira vez e baixar as dependências do projeto)
o maven baixará as dependências necessárias 

### 3. Crie e configure o banco na aplicação
(sugestão de banco Postgres)
-- Execute no PostgreSQL
CREATE DATABASE Nome_do_seu_banco;
CREATE USER api_user WITH PASSWORD 'senha_segura';
GRANT ALL PRIVILEGES ON DATABASE Nome_do_seu_banco TO api_user;

### 4. Crie uma conta no auth0
Crie uma conta no Auth0 e configure uma API com os seguintes scopes:

read:contatos

create:contatos

update:contatos

delete:contatos

A aplicação só permitirá acesso aos endpoints se um token com essas permissões for utilizado.
## 5. Configure o properties
 --- Exemplo de config ----


Spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_seu_banco

spring.datasource.username=seu_user
spring.datasource.password=sua_senha

auth0.audience=seu_auth0_audience
auth0.issuer=seu_auth0_issuer (**Lembrar de no final da url colocar "/" para funcionar corretamente)
auth0.clientId=seu_clientID
auth0.clientSecret=seu_client_secret

## 6. Executando a aplicação
agora compile o arquivo com os testes unitários 

mvn clean install

Após tudo configurado, inicie a API com:

mvn spring-boot:run

## 7. Testando a Api com SWAGGER
Após a aplicação iniciar, acesse o Swagger UI:
🔗 http://localhost:8080/swagger-ui.html

Gere um token no endpoint POST /token.

Copie o token gerado.

No Swagger, clique em Authorize e cole o token para autenticar.

Agora você pode testar os endpoints da API.

## 8. Utilizando o arquivo index.html para o front
Caso queira utilizar o front-end da aplicação é necessário configurar a url do projeto para o localhost

navegue até a pasta do projeto: 
src/main/java/resources/static e abra o arquivo index.html com um editor de código

altere os seguintes trechos de código
exemplo:
const apiUrl = 'http://localhost:8080/api/contatos';
const response = await fetch('http://localhost:8080/api/token'

## 9. Acessando o Front
Com a api em execução acesse a url:
'http://localhost:8080/index.html'

## 10 ❓ Dúvidas ou dificuldades
Entre em contato:
📩 henriquehvl1@gmail.com
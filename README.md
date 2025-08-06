# 🌐 Projeto Chapecos - Aplicação Backend Java

---

## 📌 Descrição do Projeto

O **Projeto Chapecos** é uma aplicação backend desenvolvida como parte do **Tech Challenge** da **FIAP**. O objetivo principal é criar um sistema robusto para o **gerenciamento de usuários**, utilizando o _framework_ **Spring Boot** e seguindo as melhores práticas de desenvolvimento de software.

A aplicação é **containerizada com Docker** e utiliza **PostgreSQL** como banco de dados relacional. A arquitetura foi projetada para ser **segura**, **escalável** e de fácil **manutenção**, com suporte a operações como:

- Cadastro de usuários;
- Atualização de informações;
- Exclusão de usuários;
- Validação de login;
- Troca de senha;
- Gerenciamento de permissões (_roles_);
- Geração e validação de tokens JWT.

---

## 📄 Arquitetura do Projeto

A arquitetura segue o padrão **Arquitetura em Camadas** (_Layered Architecture_), amplamente utilizado em aplicações Spring Boot. Essa abordagem organiza o código em camadas distintas, separando responsabilidades e facilitando a manutenção e escalabilidade.

### Camadas da Arquitetura

1. **Camada de Apresentação (_Web / API_)**:
   - Responsável por expor os endpoints REST.
   - Inclui controladores (_controllers_) e objetos de transferência de dados (_DTOs_).

2. **Camada de Serviço (Negócio)**:
   - Contém a lógica de negócio e regras específicas do sistema.
   - Inclui serviços, mapeadores (_mappers_) e tratamento de exceções.

3. **Camada de Persistência (Dados)**:
   - Gerencia a comunicação com o banco de dados.
   - Inclui repositórios (_repositories_) e entidades (_models_).

4. **Camada de Infraestrutura**:
   - Abrange configurações técnicas, como segurança (_security_), inicialização de dados (_initializer_) e configurações gerais (_config_).

---

## 🛠️ Endpoints da API

Abaixo estão os principais endpoints da API, organizados por funcionalidade:

### **Autenticação**

| Método | Endpoint                     | Descrição                  |
|--------|-------------------------------|----------------------------|
| POST   | `/api/auth/v1/login`          | Realizar login             |
| POST   | `/api/auth/v1/register`       | Registrar novo usuário     |

### **Usuários**

| Método | Endpoint                            | Descrição                  |
|--------|-------------------------------------|----------------------------|
| GET    | `/api/user/v1/find-all`             | Listar todos os usuários   |
| GET    | `/api/user/v1/find-by-id/{idUser}`  | Buscar usuário por ID      |
| PUT    | `/api/user/v1/update/{idUser}`      | Atualizar dados do usuário |
| PATCH  | `/api/user/v1/update-password/{idUser}` | Atualizar senha do usuário |
| PATCH  | `/api/user/v1/update-role/{idUser}` | Atualizar permissão (_role_) |
| DELETE | `/api/user/v1/delete/{idUser}`      | Deletar usuário            |

---

## ▶️ Instruções de Configuração e Execução

### **Pré-requisitos**

Certifique-se de ter as seguintes ferramentas instaladas:

- **Java 17** ou superior;
- **Maven** para gerenciamento de dependências;
- **Docker** e **Docker Compose** para containerização;
- **Postman** (opcional, para testes manuais).

### **Configuração do Banco de Dados**

As configurações do banco de dados estão no arquivo `application.properties`. Ajuste as credenciais conforme necessário:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/chapecos
spring.datasource.username=se_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### **Executando a Aplicação**

1. **Subir os contêineres Docker**:
   No diretório raiz do projeto, execute o comando abaixo para iniciar os contêineres:
   ```bash
   docker-compose up --build
   ```

2. **Acessar o Swagger UI**:
   Após iniciar a aplicação, acesse a documentação interativa da API no navegador:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

3. **Testar a API com Postman**:
   Utilize a _collection_ Postman incluída no projeto ([`postman/Chapecos.postman_collection.json`](postman/Chapecos.postman_collection.json )) para realizar testes nos endpoints.

4. **Executar Testes Automatizados**:
   Para rodar os testes automatizados, utilize o comando:
   ```bash
   mvn test
   ```

---

## 🧪 Exemplos de Requisições

### **Login**

**Endpoint**: `POST /api/auth/v1/login`  
**Exemplo de Corpo da Requisição**:
```json
{
  "identifier": "Admin",
  "password": "admin"
}
```

---

### **Registrar Usuário**

**Endpoint**: `POST /api/auth/v1/register`  
**Exemplo de Corpo da Requisição**:
```json
{
  "email": "matteus.abreu@hotmail.com",
  "userName": "Matteus Abreu",
  "password": "SenhaSegura123#",
  "address": {
    "postalCode": "04857-580",
    "street": "Rua Batistta Locatello",
    "number": "88",
    "neighborhood": "Jardim Varginha",
    "city": "São Paulo",
    "state": "SP",
    "country": "Brasil"
  }
}
```

---

### **Atualizar Usuário**

**Endpoint**: `PUT /api/user/v1/update/{idUser}`  
**Exemplo de Corpo da Requisição**:
```json
{
  "email": "matteus.abreu@hotmail.com",
  "userName": "Matteus Abreu",
  "address": {
    "postalCode": "04884-020",
    "street": "Rua Nicolas Bernier",
    "number": "20",
    "neighborhood": "Parelheiro",
    "city": "São Paulo",
    "state": "SP",
    "country": "Brasil"
  }
}
```

---

### **Atualizar Senha**

**Endpoint**: `PATCH /api/user/v1/update-password/{idUser}`  
**Exemplo de Corpo da Requisição**:
```json
{
  "currentPassword": "user",
  "newPassword": "Adm!n1"
}
```

---

### **Deletar Usuário**

**Endpoint**: `DELETE /api/user/v1/delete/{idUser}`  
**Exemplo de Requisição**:  
Sem corpo de requisição.

---

## 🧪 Testes Automatizados

Os testes automatizados foram implementados utilizando **JUnit 5** e **Mockito**. Eles cobrem as seguintes áreas principais:

1. **Controladores (_Controllers_)**:
   - Testes para verificar se os endpoints estão retornando os códigos de status corretos (200, 400, 404, etc.).
   - Validação de entradas e saídas.

2. **Serviços (_Services_)**:
   - Testes unitários para a lógica de negócios, como validação de login, atualização de dados e permissões.

3. **Repositórios (_Repositories_)**:
   - Testes para garantir que as consultas ao banco de dados estão funcionando corretamente.

Para executar os testes, utilize o comando:
```bash
mvn test
```

---

## 👨‍💻 Autores

- Francisco Aurizelio de Sousa ([GitHub](https://github.com/faurizel))
- Lucas Herculano Amaro ([GitHub](https://github.com/LucasHerculanoAmaro))
- Matheus Jesus de Souza ([GitHub](https://github.com/offteuz))
- Matteus Santos de Abreu ([GitHub](https://github.com/Nexusf1re))

---

## 🧾 Licença

Este projeto está licenciado sob a **MIT License**.

---

# 🌐 Projeto Chapecos - Aplicação Backend Java
## 📌 Objetivo
Desenvolver um backend completo e robusto utilizando o framework **Spring Boot**, com foco no **gerenciamento de usuários**, contemplando operações de:

- Criação de usuários
- Atualização de dados
- Exclusão
- Validação de login
- Troca de senha

O projeto é **containerizado com Docker** e orquestrado via **Docker Compose**, com integração a um banco de dados relacional **PostgreSQL**. 

A arquitetura foi pensada para ser **segura, escalável e de fácil manutenção**, seguindo as melhores práticas de desenvolvimento com Spring Boot.

---

## 👤 Tipos de Usuários

O sistema contempla dois perfis de usuário:

- **Dono de Restaurante**;
- **Cliente**.

---
### 📄 Dados do Usuário

Os seguintes campos são utilizados no cadastro:

- `nome`: Nome completo (String);
- `email`: Endereço de e-mail (String);
- `login`: Nome de usuário (String);
- `senha`: Senha de acesso (String);
- `dataUltimaAlteracao`: Data da última atualização (Date);
- `endereco`: Endereço completo (String).

---

## ✅ Funcionalidades Implementadas

- [x]  Cadastro de usuários;
- [x]  Atualização de informações do usuário;
- [x]  Exclusão de usuários;
- [x]  Troca de senha;
- [x]  Validação de login.

---

## 📦 Tecnologias Utilizadas

- Java 17;
- Spring Boot 3.4.4;
- Dependencias:
  - Spring Data JPA;
  - Spring Data JBDC;
  - Spring Validation;
  - Spring Security;
  - Spring Security Test;
  - Spring Web;
  - Spring Devtools;
  - Spring Docker Compose;
  - Spring Test;
  - Spring OpenAPI;
  - Flyway Core;
  - Flyway PostgreSQL;
  - PostgreSQL;
  - Lombok;
  - Mapstruct;
  - Auth0 Java JWT.
- Banco de dados PostgreSQL;
- Docker + Docker Compose;
- Maven;
- Postman API.

---

## 🐳 Executando com Docker Compose

### Pré-requisitos

- Git;
- Docker e Docker Compose instalados;

### Passos

1. Clone o repositório:
   ```
   git clone https://github.com/offteuz/chapecos.git
   cd chapecos
   ```

2. Suba os Contêiners com Docker Compose:
   ```
   docker-compose up --build
   ```

3. A API estará disponível em:
   ```
   http://localhost:8080
   ```

## 📂 Estrutura de Projeto

  ```
  src/
  ├── main/
  │   ├── java/
  │   │   └── br.com.fiap.chapecos
  │   │       ChapecosApplication.Java (Main)
  │   │       ├── config/
  │   │       ├── controller/
  │   │       ├── dto/
  │   │       ├── exception/
  │   │       ├── handler/
  │   │       ├── mapper/
  │   │       ├── model/
  │   │       ├── repository/
  │   │       └── service/
  │   └── resources/
  │       ├── application.properties
  .env
  docker-compose.yml
  ```

## 🛠️ Endpoints da API


| Método | Endpoint                            | Descrição                  |
| ------ |-------------------------------------| -------------------------- |
| POST   | `/auth/register/v0`                 | Criar novo usuário         |
| POST   | `/auth/login/v0`                    | Validação de login         |
| GET    | `/find-all/v0/user`                 | Listar todos os usuários   |
| GET    | `/find-by-id/v0/user/{idUser}`      | Buscar usuário por ID      |
| PUT    | `/update/v0/user/{idUser}`          | Atualizar dados do usuário |
| PUT    | `/update-password/v0/user/{idUser}` | Troca de senha do usuário  |
| DELETE | `/delete/v0/user/{idUser}`          | Deletar usuário            |




## 📄 Documentação Técnica

A documentação da API pode ser visualizada via **Swagger** após subir a aplicação:

  ```
  http://localhost:8080/swagger-ui.html
  ```

## 🧪 Collections para Testes

Collection do Postman incluída no repositório: 
  ```
  postman_collection.json
  ```

## 🗃️ Repositório de Código

Repositório disponível em: 
  ```
  https://github.com/offteuz/chapecos
  ```

## 🧾 Licença

Este projeto está licenciado sob a **MIT License**.

## 👨‍💻 Autor

Francisco Aurizelio de Sousa
  - `Email`: franads@gmail.com
  - `Github`:

Lucas Herculano Amaro
  - `Email`: lucash.96@hotmail.com
  - `Github`: https://LucasHerculanoAmaro

Matheus Jesus de Souza
  - `Email`: offteuz@gmail.com
  - `Github`: https://github.com/offteuz

Matteus Santos de Abreu
  - `Email`: matteussantos30@gmail.com
  - `Github`: 

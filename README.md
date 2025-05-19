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

- **Dono de Restaurante**
- **Cliente**

---
### 📄 Dados do Usuário

Os seguintes campos são utilizados no cadastro:

- `nome`: Nome completo (String)
- `email`: Endereço de e-mail (String)
- `login`: Nome de usuário (String)
- `senha`: Senha de acesso (String)
- `dataUltimaAlteracao`: Data da última atualização (Date)
- `endereco`: Endereço completo (String)

---

## ✅ Funcionalidades Implementadas

- [x]  Cadastro de usuários
- [x]  Atualização de informações do usuário
- [x]  Exclusão de usuários
- [x]  Troca de senha
- [x]  Validação de login

---

## 📦 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Banco de dados PostgreSQL
- Docker + Docker Compose
- Maven
- Postman (para testes)

---

## 🐳 Executando com Docker Compose

### Pré-requisitos

- Git
- Docker e Docker Compose instalados

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
  │   │   └── com.seuprojeto
  │   │       ├── controller/
  │   │       ├── model/
  │   │       ├── repository/
  │   │       └── service/
  │   └── resources/
  │       ├── application.properties
  docker-compose.yml
  ```

## 🛠️ Endpoints da API

| Método | Endpoint                   | Descrição                  |
| ------ | -------------------------- | -------------------------- |
| POST   | `/api/usuarios`            | Criar novo usuário         |
| GET    | `/api/usuarios`            | Listar todos os usuários   |
| GET    | `/api/usuarios/{id}`       | Buscar usuário por ID      |
| PUT    | `/api/usuarios/{id}`       | Atualizar dados do usuário |
| DELETE | `/api/usuarios/{id}`       | Deletar usuário            |
| POST   | `/api/usuarios/login`      | Validação de login         |
| PUT    | `/api/usuarios/senha/{id}` | Troca de senha do usuário  |


## 📄 Documentação Técnica

A documentação da API pode ser visualizada via Swagger após subir a aplicação:

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

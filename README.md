# 🌐 Projeto Chapecos - Aplicação Backend Java

---

## 📚 Índice

- [Objetivo](#-objetivo)
- [Dados do Usuário](#-dados-do-usuário)
- [Funcionalidades Implementadas](#-funcionalidades-implementadas)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura da Aplicação](#-arquitetura-da-aplicação)
- [Estrutura do Projeto](#-estrutura-de-projeto)
- [Endpoints da API](#-endpoints-da-api)
- [Documentação Técnica](#-documentação-técnica)
- [Collections para Testes](#-_collections_-para-testes)
- [Executando a Aplicação](#-executando-a-aplicação)
  - [1º Passo: Executando o contêiner Docker](#1º-passo-executando-o-contêiner-docker)
  - [2º Passo: Observando o pgAdmin](#2º-passo-observando-o-pgadmin)
  - [3º Passo: Swagger UI](#3º-passo-swagger-ui)
  - [4º Passo: Testes com Postman](#4º-passo-testes-com-postman)
    - [1 - Login | POST](#1---_login_-do-usuário-teste-com-método-_post_)
    - [2 - Register | POST](#2---_register_-com-método-_post_)
    - [3 - Find All | GET](#3---_find-all_-com-método-_get_)
    - [4 - Find by ID | GET](#4---_find-by-id_-com-método-_get_)
    - [5 - Update User | PUT](#5---_update_-com-método-_put_)
    - [6 - Update Password | PATCH](#6---_update-password_-com-método-_patch_)
    - [7 - Update Role | PATCH](#7---_update-role_-com-método-_patch_)
    - [8 - Delete | DELETE](#8---_delete_-com-método-_delete_)
- [Testes Automatizados](#-testes-automatizados)
- [Configurações Adicionais](#-configurações-adicionais)
- [Autores](#-autores)
- [Licença](#-licença)

---

## 📌 Objetivo

Este é um projeto **Tech Challenge** da **FIAP**, e o objetivo é desenvolver um _backend_ completo e robusto utilizando o _framework_ **Spring Boot**, com foco no **gerenciamento de usuários**, contemplando operações de:

- Criação de _login_ de usuário;
- Atualização dos dados de cadastro;
- Exclusão de _login_;
- Validação de _login_;
- Troca de senha.

O projeto é **containerizado com Docker** e orquestrado via **Docker Compose**, com integração a um banco de dados relacional **PostgreSQL**. 

A arquitetura foi pensada para ser **segura**, **escalável** e de fácil **manutenção**, seguindo as melhores práticas de desenvolvimento com **Spring Boot**.

---

## 📄 Dados do Usuário

O sistema contempla dois perfis de usuário:

- **ADMIN** - para usuários de alto nível de permissão;
- **USER** - para usuários de baixo nível de permissão.

Os seguintes campos são utilizados no cadastro:

- `nome`: Nome completo (_String_);
- `email`: Endereço de e-mail (_String_);
- `login`: Nome de usuário (_String_);
- `senha`: Senha de acesso (_String_);
- `dataUltimaAlteracao`: Data da última atualização (_Date_);
- `endereco`: Endereço completo (_String_).

---

## ✅ Funcionalidades Implementadas

A aplicação deve ser capaz de proporcionar ao usuário as seguintes funcionalidades:

- [x] Cadastro de usuários;
- [x] Atualização de informações do usuário;
- [x] Exclusão de usuários;
- [x] Troca de senha;
- [x] Validação de _login_;
- [x] Gerenciamento de permissões (_roles_);
- [x] Geração e validação de tokens JWT.

---

## 📦 Tecnologias Utilizadas

- **Java 17**;
- _Spring Boot_ 3.4.4;
- Dependências:
  - _Spring Data JPA_;
  - _Spring Data JDBC_;
  - _Spring Validation_;
  - _Spring Security_;
  - _Spring Security Test_;
  - _Spring Web_;
  - _Spring Devtools_;
  - _Spring Docker Compose_;
  - _Spring Test_;
  - _Spring OpenAPI_;
  - _Flyway Core_;
  - _Flyway PostgreSQL_;
  - _PostgreSQL_;
  - _Lombok_;
  - _Mapstruct_;
  - _Auth0 Java JWT_.
- Banco de dados PostgreSQL;
- Docker + Docker Compose;
- Maven;
- Postman API.

---

## 📄 Arquitetura da Aplicação

A arquitetura aplicada de nosso projeto segue o padrão Arquitetura em Camadas (_Layered Architecture_), típica de aplicações desenvolvidas com o _framework_ **Spring Boot**. Esta arquitetura mantém a organização das classes e pacotes separados, mantendo a clareza sobre as responsabilidades.

As camadas observadas são:

- **Camada de Apresentação (_Web / API_)**: Responsável por expor os endpoints _REST_ via controladores e tratar entradas e saídas com _DTOs_. Inclui o pacote "_controller_", os _DTOs_ no pacote **_dto_**, e os mapeamentos de exceções.

- **Camada de Serviço (Negócio)**: Contém a lógica de negócio, autenticação e regras específicas do sistema. São representadas pelo pacote **_service_** e apoiada pelo pacote **_mapper_** e pelo pacote **_exception_**.

- **Camada de Persistência (Dados)**: Representada pelas entidades **_model_** e pelos repositórios _JPA_ (_repository_) responsáveis pela comunicação com o banco de dados.

- **Camada de Infraestrutura**: Abrange configurações técnicas do projeto, como segurança (_security_) e configuração da _API_ (_config_) e inicialização de dados para os primeiros testes (_initializer_).

Essa divisão facilita a manutenção, a realização de testes e escalabilidade da aplicação.

Veja o diagrama que representa as camadas da aplicação:

|     **Camada de API**      |
|:--------------------------:|
|  (_Controller_ + _DTOs_)   |
|             ↓              |
|   **Camada de Serviço**    |
| (Regras + _TokenService_)  |
|             ↓              |
| **Camada de Persistência** |
|  (_Repository_ + _Model_)  |
|             ↓              |
|     **Banco de Dados**     |

---

## 📂 Estrutura de Projeto

A estrutura do projeto segue o padrão de organização do **Spring Boot**:

```
├───src
│   ├───main
│   │   ├───java
│   │   │   └───br
│   │   │       └───com
│   │   │           └───fiap
│   │   │               └───chapecos
│   │   │                   ├───config
│   │   │                   ├───controller
│   │   │                   ├───dto
│   │   │                   ├───exception
│   │   │                   ├───handler
│   │   │                   ├───mapper
│   │   │                   ├───model
│   │   │                   ├───repository
│   │   │                   └───service
│   │   └───resources
│   └───test
│       └───java
│           └───br
│               └───com
│                   └───fiap
│                       └───chapecos
```

---

## 🛠️ Endpoints da API

| Método | Endpoint                            | Descrição                  |
| ------ |-------------------------------------|----------------------------|
| POST   | `/auth/register/v0`                 | Criar novo usuário         |
| POST   | `/auth/login/v0`                    | Validação de _login_       |
| GET    | `/find-all/v0/user`                 | Listar todos os usuários   |
| GET    | `/find-by-id/v0/user/{idUser}`      | Buscar usuário por _ID_    |
| PUT    | `/update/v0/user/{idUser}`          | Atualizar dados do usuário |
| PUT    | `/update-password/v0/user/{idUser}` | Troca de senha do usuário  |
| DELETE | `/delete/v0/user/{idUser}`          | Deletar usuário            |

---

## 📄 Documentação Técnica

A documentação da _API_ pode ser visualizada via **Swagger** após subir a aplicação:

```
http://localhost:8081/swagger-ui.html
```

---

## 🧪 _Collections_ para Testes

_Collection_ do Postman incluída no repositório: 

```
postman_collection.json
```

---

## ▶️ Executando a Aplicação

Agora vamos analisar passo-a-passo para executar e testar a aplicação. 

### 1º Passo: Executando o contêiner Docker

Abra o terminal do projeto **Chapecos**, e use o comando abaixo para executar o contêiner Docker:

```
docker-compose up --build
```

### 2º Passo: Observando o pgAdmin

Acesse o **pgAdmin** para verificar o banco de dados.

### 3º Passo: Swagger UI

Acesse a URL abaixo para ter acesso à documentação do Swagger:

```
http://localhost:8081/swagger-ui/index.html
```

### 4º Passo: Testes com Postman

Realize os testes conforme descrito na seção de endpoints.

---

## 🧪 Testes Automatizados

Os testes automatizados estão configurados com **JUnit 5** e **Mockito**. Para executá-los, utilize o comando:

```
mvn test
```

---

## ⚙️ Configurações Adicionais

As configurações do banco de dados estão no arquivo `application.properties`. Certifique-se de ajustar as credenciais conforme necessário.

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
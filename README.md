# 🌐 Projeto Chapecos - Aplicação Backend Java

---

## 📚 Índice

- [Objetivo](#-objetivo)
- [Dados do Usuário](#-dados-do-usuário)
- [Funcionalidades Implementadas](#-funcionalidades-implementadas)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura da Aplicação](#-arquitetura-da-aplicação)
- [Estrutura do Projeto](#-estrutura-de-projeto)
- [Endpoints da API](#%EF%B8%8F-endpoints-da-api)
- [Documentação Técnica](#-documentação-técnica)
- [Collections para Testes](#-collections-para-testes)
- [Executando a Aplicação](#%EF%B8%8F-executando-a-aplicação)
  - [1º Passo: Executando o contêiner Docker](#1º-passo-executando-o-contêiner-docker)
  - [2º Passo: Observando o pgAdmin](#2º-passo-observando-o-pgadmin)
  - [3º Passo: Swagger UI](#3º-passo-swagger-ui)
  - [4º Passo: Testes com Postman](#4º-passo-testes-com-postman)
    - [1 - Login | POST](#1%EF%B8%8F⃣---login-do-usuário-teste-com-método-post)
    - [2 - Register | POST](#2%EF%B8%8F⃣---register-com-método-post)
    - [3 - Find All | GET](#3%EF%B8%8F⃣---find-all-com-método-get)
    - [4 - Find by ID | GET](#4%EF%B8%8F⃣---find-by-id-com-método-get)
    - [5 - Update User | PUT](#5%EF%B8%8F⃣---update-com-método-put)
    - [6 - Update Password | PATCH](#6%EF%B8%8F⃣---update-password-com-método-patch)
    - [7 - Update Role | PATCH](#7%EF%B8%8F⃣---update-role-com-método-patch)
    - [8 - Delete | DELETE](#8%EF%B8%8F⃣---delete-com-método-delete)
  - [Autores](#-autores)
  - [Licença](#-licença)

---

## 📌 Objetivo
Este é um projeto **Tech Challenge** da **FIAP**, e o objetivo é desenvolver um
_backend_ completo e robusto utilizando o _framework_ **Spring Boot**, com foco no 
**gerenciamento de usuários**, contemplando operações de:

- Criação de _login_ de usuário;
- Atualização dos dados de cadastro;
- Exclusão de _login_;
- Validação de _login_;
- Troca de senha.

O projeto é **containerizado com Docker** e orquestrado via **Docker Compose**, 
com integração a um banco de dados relacional **PostgreSQL**. 

A arquitetura foi pensada para ser **segura**, **escalável** e de fácil 
**manutenção**, seguindo as melhores práticas de desenvolvimento com 
**Spring Boot**.

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

- [x]  Cadastro de usuários;
- [x]  Atualização de informações do usuário;
- [x]  Exclusão de usuários;
- [x]  Troca de senha;
- [x]  Validação de _login_.

---

## 📦 Tecnologias Utilizadas

- Java 17;
- _Spring Boot_ 3.4.4;
- Dependencias:
  - _Spring Data JPA_;
  - _Spring Data JBDC_;
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

## 🏗️ Arquitetura da Aplicação

A arquitetura aplicada de nosso projeto segue o padrão Arquitetura em Camadas 
(_Layered Architecture_), típica de aplicações desenvolvidas com o _framework_
**Spring Boot**. Esta arquitetura mantém a organização das classes e pacotes separados,
mantendo a clareza sobre as responsabilidades.

As camadas observadas são:

- **Camada de Apresentação (_Web / API_)**: Responsável por expor os endpoints 
_REST_ via controladores e tratar entradas e saídas com _DTOs_. Inclui o pacote 
"_controller_", os _DTOs_ no pacote **_dto_**, e os mapeamentos de exceções.


- **Camada de Serviço (Negócio)**: Contém a lógica de negócio, autenticação e regras 
específicas do sistema. São representadas pelo pacote **_service_** e apoiada pelo 
pacote **_mapper_** e pelo pacote **_exception_**.


- **Camada de Persistência (Dados)**: Representada pelas entidades **_model_** e 
pelos repositórios _JPA_ (_repository_) responsáveis pela comunicação com o banco de
dados.


- **Camada de Configuração (Comunicação)**: Abrange configurações técnicas do projeto, como 
segurança (_security_) e configuração da _API_ (_config_) e inicialização de dados 
para os primeiros testes (_initializer_).

Essa divisão facilita a manutenção, a realização de testes e escalabilidade da aplicação.

Veja o diagrama que representa as camadas da aplicação:

|            🧩 **Camada de API**             |
|:-------------------------------------------:|
| (_Controller_, _DTOs_, _ExceptionHandler_)  |
|                      ↓                      |
|          🧠 **Camada de Serviço**           |
| (Regras + Autenticação, _Mapper_, Exceções) |
|                      ↓                      |
|        💾 **Camada de Persistência**        |
|          (_Repository_ + _Model_)           |
|                      ↓                      |
|        ⚙️ **Camada de Configuração**        |
|   Segurança, Configuração, Inicialização    |
|                      ↓                      |
|          🗄️   **Banco de Dados**           |

---

## 📂 Estrutura de Projeto

    src
    └───main
        ├───java
        │   └───br
        │       └───com
        │           └───fiap
        │               └───chapecos
        │                   ├───config
        │                   │   ├───configuration
        │                   │   ├───initializer
        │                   │   └───security
        │                   ├───controller
        │                   ├───dto
        │                   │   ├───request
        │                   │   └───response
        │                   ├───exception
        │                   ├───handler
        │                   ├───mapper
        │                   ├───model
        │                   ├───repository
        │                   └───service
        └───resources

---

## 🛠️ Endpoints da API


| Método | Endpoint                            | Descrição                  |
| ------ |-------------------------------------|----------------------------|
| POST   | `/auth/register/v1`                 | Criar novo usuário         |
| POST   | `/auth/login/v1`                    | Validação de _login_       |
| GET    | `/find-all/v1/user`                 | Listar todos os usuários   |
| GET    | `/find-by-id/v1/user/{idUser}`      | Buscar usuário por _ID_    |
| PUT    | `/update/v1/user/{idUser}`          | Atualizar dados do usuário |
| PUT    | `/update-password/v1/user/{idUser}` | Troca de senha do usuário  |
| DELETE | `/delete/v1/user/{idUser}`          | Deletar usuário            |

---

## 📄 Documentação Técnica

A documentação da _API_ pode ser visualizada via **Swagger** após subir a aplicação:

  ```
  http://localhost:8080/swagger-ui.html
  ```
---

## 🧪 Collections para Testes

_Collection_ do Postman incluída no repositório: 
  ```
  postman_collection.json
  ```
---

## ▶️ Executando a Aplicação

Agora vamos analisar passo-a-passo para executar e testar a aplicação. 
Os passos para executar a aplicação serão partirão do princípio que o projeto já 
está devidamente instalado no ambiente de trabalho.

### 1º Passo: Executando o contêiner Docker

Abra o terminal do projeto **Chapecos**, e use o comando abaixo para executar o 
contêiner Docker:

    docker-compose up --build

Este comando fará a executação da imagem Docker e, posteriormente, da aplicação 
**Chapecos** de forma automática. 

### 2º Passo: Observando o pgAdmin

Em nossa aplicação foi configurado um usuário teste que é cadastrado 
automaticamente no Banco de Dados e tem permissão sendo um usuário do tipo **Admin**.

Esta implementação foi desenvolvida pensando em um nível superior de segurança, 
onde apenas quem pode realizar certas requisições seja alguém que esteja com 
permissões para isso.

A _URL_ abaixo te guiará ao **pgAdmin**:

    http://localhost:8082/login?next=/browser/

No documento **.env** é possível encontrar o login e senha para acessar o 
**pgAdmin**, sendo eles:

    Login = matheus@email.com
    Senha = admin

Ao clicar em **Server**, será exibido uma tela com as seguintes informações:

    Connect to Server

    Please enter the password for the user 'admin' to connect the server - "database"

Você deve adicionar a mesma senha que utilizou para acessar o **pgAdmin**: admin.

Após liberar o acesso ao banco _**database**_, clique no icone **_Query Tools_**
e escreva o seguinte _script_:

    select id_user, user_name, email from t_user;

O resultado esperado deve ser:

| **id_user** | **user_name** | **email**          |
|------------:|---------------|--------------------|
|           1 | Admin         | admin@chapecos.com |

**Obs**: Você também pode executar "_select * from t_user;_" para ter acesso ao
inteiro cadastro do usuário.

### 3º Passo: Swagger UI

Acesse a URL abaixo para ter acesso a documentação do Swagger.
    
    http://localhost:8080/swagger-ui/index.html

**Obs**: Vamos fazer os testes diretamente no Postman.

### 4º Passo: Testes com Postman

Já abordamos os _**endpoints**_ de nossa aplicação anteriormente, e agora vamos 
testá-los. 

Inicialmente, testaremos o usuário já cadastrado no banco de dados.

Lembrando que na _collection_ do postman, temos algumas configurações já 
definidas para serem testadas.

#### 1️⃣ - _Login_ do usuário teste com método _POST_

Confira abaixo a configuração de _Login_ da _collection_: 
**Chapecos / auth / Login**.

    Método: POST
    URL: http://localhost:8080/api/auth/v1/login
    Body (raw, formato: JSON):
        {
            "identifier": "Admin",
            "password": "admin"
        }

Resultado:
    
    Status Code: 200 OK
    Body:
        {
            "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJDaGFwZWNvcyBBUEkiLCJzdWIiOiJBZG1pbiIsImV4cCI6MTc0ODkxODU0Mn0.effNDb8K6UW8rfafya6yYUc1r6foBsgpx4-uVt9k44c"
        }

    Obs: O token pode ser diferente.

Nosso primeiro teste foi bem sucedido. Agora copie este token, será necessário 
para os próximos testes. 

#### 2️⃣ - _Register_ com método _POST_

Confira abaixo a configuração de _Register_ do _collection_: 
**Chapecos / auth / Register**.

    Método: POST
    URL: http://localhost:8080/api/auth/v1/register
    Body (raw, formato: JSON):
        {
        "email": "lucash.96@hotmail.com",
        "userName": "lucas",
        "password": "user",
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

Resultado:
    
    Status Code: 200 OK

Nosso segundo teste foi bem sucedido.
No próximo teste, vamos conseguir observar a lista dos usuários já cadastrados até o momento.

#### 3️⃣ - _Find All_ com método _GET_

Confira abaixo a configuração de _Find All_ da _collection_: 
**Chapecos / Controller / Find All**.

    Método: GET
    URL: http://localhost:8080/api/user/v1/find-all

**Obs**: Você precisa pegar o _Token_ enviado no momento do _login_ do usuário teste 
e enviar no _**Headers**_ desta requisição, conforme observamos abaixo:

| Key             | Value                                   |
|-----------------|-----------------------------------------|
| _Authorization_ | _Token_ oferecido no momento do _login_ |

Sem este _token_, a requisição será negada com _Status Code_ **403**. 

Resultado:

    [
        {
            "id": 1,
            "email": "admin@chapecos.com",
            "userName": "Admin",
            "address": {
                "postalCode": "36500-067",
                "street": "Avenida Raul Soares",
                "number": "96",
                "neighborhood": "Centro",
                "city": "Ubá",
                "state": "MG",
                "country": "Brasil"
            },
            "role": "ADMIN",
            "audit": {
                "createdAs": "30-05-2025 00:35:19",
                "updatedAs": "30-05-2025 00:35:19"
            }
        },
        {
            "id": 2,
            "email": "lucash.96@hotmail.com",
            "userName": "lucas",
            "address": {
                "postalCode": "04857-580",
                "street": "Rua Batistta Locatello",
                "number": "88",
                "neighborhood": "Jardim Varginha",
                "city": "São Paulo",
                "state": "SP",
                "country": "Brasil"
            },
            "role": "USER",
            "audit": {
                "createdAs": "02-06-2025 21:59:55",
                "updatedAs": "02-06-2025 21:59:55"
            }
        }
    ]
    
#### 4️⃣ - _Find by ID_ com método _GET_

Confira abaixo a configuração de _Find All_ da _collection_: 
**Chapecos / Controller / Find by ID**.

    Método: GET
    URL: http://localhost:8080/api/user/v1/find-by-id/2
    Headers:
        Authorization = Seu token

Resultado:

    {
        "id": 2,
        "email": "lucash.96@hotmail.com",
        "userName": "lucas",
        "address": {
            "postalCode": "04857-580",
            "street": "Rua Batistta Locatello",
            "number": "88",
            "neighborhood": "Jardim Varginha",
            "city": "São Paulo",
            "state": "SP",
            "country": "Brasil"
        },
        "role": "USER",
        "audit": {
            "createdAs": "02-06-2025 21:59:55",
            "updatedAs": "02-06-2025 21:59:55"
        }
    }

#### 5️⃣ - _Update_ com método _PUT_

Confira abaixo a configuração de _Update_ da _collection_:
**Chapecos / Controller / Update**.

    Método: PUT
    URL: http://localhost:8080/api/user/v1/update/2
    Headers:
      Authorization: Seu token

Imagine que nosso usuário está com o nome incompleto na plataforma e precisa 
atualizar. Além disso, ele se mudou e agora vive em um novo endereço.

Para atualizar, será necessário enviar o seguinte **_JSON_** no corpo da requisição:

    {
      "email": "lucash.96@hotmail.com",
      "userName": "lucas amaro",
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

Resultado:
    
    Status Code: 200 OK

    {
      "id": 2,
      "email": "lucash.96@hotmail.com",
      "userName": "lucas amaro",
      "address": {
          "postalCode": "04884-020",
          "street": "Rua Nicolas Bernier",
          "number": "20",
          "neighborhood": "Parelheiro",
          "city": "São Paulo",
          "state": "SP",
          "country": "Brasil"
      },
      "role": "USER",
      "audit": {
          "createdAs": "02-06-2025 22:51:07",
          "updatedAs": "02-06-2025 22:51:07"
      }
    }

Ao consultar com a collection **_Find by ID_** ou com **_Find All_**, teremos o 
retorno no do objeto com as alterações já incluídas.

#### 6️⃣ - _Update Password_ com método _PATCH_

Confira abaixo a configuração de _Update_ da _collection_:
**Chapecos / Controller / Update Password**.

    Método: Patch
    URL: http://localhost:8080/api/user/v1/update-password/2


Ainda não testamos o _login_ do novo usuário. Precisamos testá-lo antes de enviar 
a atualizar a sua senha.

Na _collection_ **_Login_**, basta alterar as informações do usuário **Admin** para
o novo usuário que criamos, conforme **_JSON_** abaixo:

    {
      "identifier": "lucash.96@hotmail.com",
      "password": "user"
    }

Feito isso, receberemos um novo _token_ em um _Status Code_ **200**:

    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJDaGFwZWNvcyBBUEkiLCJzdWIiOiJsdWNhcyBhbWFybyIsImV4cCI6MTc0ODkyMzQwMX0.rMCZoSvxhU5n7Xw_EcJ1pmkGlBY-9TsJP3tYJ9J9X48"
    }

    Obs: O token pode ser diferente.

Sabemos que o usuário e a senha estão corretos, mas agora vamos atualizar a senha
desse usuário. 

Vá na _collection_ **_Update Password_** insira a senha atual e a nova senha, 
conforme exemplo abaixo:

    { 
      // Senha atual
      "currentPassword": "user", 

      // Nova Senha
      "newPassword": "Adm!n1"      
    }

    Obs: A nova senha deve conter letras maiúsculas, minúsculas, caracteres especiais e números.

Vamos retornar a _collection_ **_Login_**, e lá vamos fazer novamente o login com a 
nova senha, conforme exemplo abaixo:

    {
      "identifier": "lucash.96@hotmail.com",
      "password": "Adm!n1"
    }

Resultado:

    Status Code: 200 Ok
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJDaGFwZWNvcyBBUEkiLCJzdWIiOiJsdWNhcyBhbWFybyIsImV4cCI6MTc0ODkyNDQxMH0.cDuJLBoA6TG0Ngg7gGXdYZdnIYZuQx-mO-8NDqOIJLc"
    }

#### 7️⃣ - _Update Role_ com método _PATCH_

Confira abaixo a configuração de _Update_ da _collection_: 
**Chapecos / Controller / Update Role**.

    Método: Patch
    URL: http://localhost:8080/api/user/v1/update-role/2
    Headers:
      Authorization: Seu Token.

Antes de atualizar é importante observar o estado atual da tabela **t_user** no 
**pgAdmin**. Sera interessante acessar consultar a _Role_ que cada usuário tem 
com o seguinte _script_:

    SELECT user_name, role FROM t-user;

O resultado da consulta será:

| **user_name** | **role** |
|:-------------:|:--------:|
|     Admin     |    0     |
|  lucas amaro  |    1     |

Agora que sabemos que há um usuário com a _Role_ **0** e outro com a _Role_ **1**,
vamos enviar seguinte **_JSON_** no corpo da requisição do **_Update Role_**:

    {
      "role": "ADMIN"
    }

Resultado:

    Status Code: 403 Forbidden

Porque não foi possível atualizar a _Role_?

A explicação é simples: não é interessante, e muito menos seguro, que qualquer 
usuário que não é administrador consiga "escalar" a sua permissão, passando de 
**_USER_** para **_ADMIN_**. Imagina como isso é perigoso!

Por isso, para executar essa requisição, apenas um usuário com permissões 
avançadas podem alterar a permissão de outros usuários.

Para mudar isso vamos voltar a _collection_ **_Login_** e:

1 - Fazer o _login_ com o usuário **Admin**: 

    {
      "identifier": "Admin",
      "password": "admin"
    }

2 - Copiar o _token_ retornado:

    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJDaGFwZWNvcyBBUEkiLCJzdWIiOiJBZG1pbiIsImV4cCI6MTc0ODkyNTc2Nn0.giPc6mIU7HosvrDyc1HjUIxboXL3p2yL071zFpCrICY"
    }

    Obs: O token pode ser diferente.

3 - Inserir o _token_ no **_Header_** > **_Authorization_** da _collection_ 
**_Update Role_**:

| Key             | Value       |
|-----------------|-------------|
| _Authorization_ | Seu _Token_ |

4 - Enviar a requisição.

Resultado:

    Status Code: 200 OK

Vamos observar como está a tabela no **pgAdmin**?

| **user_name** | **role** |
|:-------------:|:--------:|
|     Admin     |    0     |
|  lucas amaro  |    0     |

Agora o usuário **lucas amaro** é um **administrador** e pode realizar 
requisições que outros usuário do tipo **_USER_** não podem.

#### 8️⃣ - **_Delete_** com método **_DELETE_**

Confira abaixo a configuração de _DELETE_ da _collection_: 
**Chapecos / Controller / Delete**.

    Método: Delete
    URL: http://localhost:8080/api/user/v1/delete/2
    Headers: 
      Authorization: Seu Token

Para testar este último método, realizei novamente o **_Login_** com o usuário
**lucash.96@hotmail.com** para testar o método **_Delete_** como um **_ADMIN_**.

Obs: Este método só pode ser executado por usuários com permissão do tipo 
**_ADMIN_**.

Antes de executar, vale lembrar que a nossa tabela tinha duas **tuplas**:

| **id_user** | **user_name** | **email**             |
|------------:|---------------|-----------------------|
|           1 | Admin         | admin@chapecos.com    |
|           2 | lucas amaro   | lucash.96@hotmail.com |

Depois de executar, a nossa tabela passou a ter apenas uma **tupla**:

| **id_user** | **user_name** | **email**          |
|------------:|---------------|--------------------|
|           1 | Admin         | admin@chapecos.com |

Dessa forma, nosso objeto não existe mais, e a nossa aplicação conta com apenas
um usuário cadastrado no banco de dados.

---

## 👨‍💻 Autores

Francisco Aurizelio de Sousa
  - `Email`: franads@gmail.com
  - `Github`: https://github.com/faurizel

Lucas Herculano Amaro
  - `Email`: lucash.96@hotmail.com
  - `Github`: https://github.com/LucasHerculanoAmaro

Matheus Jesus de Souza
  - `Email`: offteuz@gmail.com
  - `Github`: https://github.com/offteuz

Matteus Santos de Abreu
  - `Email`: matteussantos30@gmail.com
  - `Github`: https://github.com/Nexusf1re

---

## 🧾 Licença

Este projeto está licenciado sob a **MIT License**.

---

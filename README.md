# 🌱 AgroSpace API

## 👨‍🎓 Integrante

**Nome:** Emily Maria de Olivera Macedo
**RM:** 554882

---

## 📖 Sobre o Projeto

A AgroSpace API é uma aplicação desenvolvida em Java utilizando Spring Boot para monitoramento de áreas agrícolas por meio de dados coletados por satélites.

O projeto simula um cenário de Agricultura Espacial, permitindo o gerenciamento de fazendas, áreas agrícolas, satélites e leituras ambientais. A solução busca auxiliar na análise de condições ambientais e na identificação de riscos para a produção agrícola.

## 🎯 Objetivo

Desenvolver uma API REST capaz de gerenciar informações agrícolas e ambientais utilizando boas práticas de desenvolvimento com Spring Boot, aplicando conceitos de Java Advanced.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring HATEOAS
- Spring Cache
- H2 Database
- Swagger / OpenAPI
- Maven
- Lombok

---

## 📂 Estrutura do Projeto

```text
src/main/java/br/com/fiap/agrospace

├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── repository
├── service
├── config
└── AgrospaceApplication
```

---

## 🚀 Funcionalidades

### Fazendas

- Cadastrar fazenda
- Consultar fazendas
- Consultar fazenda por ID
- Atualizar fazenda
- Remover fazenda

### Áreas Agrícolas

- Cadastrar área agrícola
- Consultar áreas agrícolas
- Consultar área agrícola por ID
- Atualizar área agrícola
- Remover área agrícola

### Satélites

- Cadastrar satélite
- Consultar satélites
- Consultar satélite por ID
- Atualizar satélite
- Remover satélite

### Leituras Ambientais

- Registrar leitura ambiental
- Consultar leituras
- Consultar leitura por ID
- Atualizar leitura
- Remover leitura

---

## ⚙️ Recursos Implementados

### API REST

- CRUD completo
- DTOs (Data Transfer Objects)
- Separação em camadas

### Validação

- Bean Validation
- Mensagens personalizadas de erro

### Tratamento de Exceções

- GlobalExceptionHandler
- ResourceNotFoundException
- Respostas padronizadas para erros

### Segurança

- Spring Security
- Autenticação Basic Auth

### Recursos Avançados

- HATEOAS
- Paginação
- Cache
- Relacionamentos JPA
- @Embedded
- @Embeddable

### Documentação

- Swagger/OpenAPI

---

## 🔒 Credenciais de Acesso

Usuário:

```text
admin
```

Senha:

```text
admin123
```

---

## ▶️ Como Executar o Projeto

### 1. Clonar o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

### 2. Entrar na pasta do projeto

```bash
cd agrospace
```

### 3. Executar a aplicação

```bash
mvn spring-boot:run
```

---

## 📚 Documentação Swagger

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui.html
```

---

## 🗄️ Banco de Dados H2

Acessar:

```text
http://localhost:8080/h2-console
```

Configuração:

```text
JDBC URL: jdbc:h2:mem:agrospace
User: sa
Password:
```

---

## 📌 Exemplos de Recursos

### Fazenda

Representa uma propriedade agrícola monitorada pelo sistema.

### Área Agrícola

Representa uma área cultivável pertencente a uma fazenda.

### Satélite

Responsável pelo monitoramento remoto das áreas agrícolas.

### Leitura Ambiental

Armazena informações como:

- Temperatura
- Umidade
- Índice de vegetação
- Nível de risco

---

## 👨‍💻 Desenvolvido para

Global Solution – FIAP

Disciplina: Java Advanced

Tema: Agricultura Espacial e Monitoramento Inteligente de Áreas Agrícolas
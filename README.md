# 🎬 ScreenMatch Microservices

Sistema de gerenciamento de filmes desenvolvido utilizando arquitetura de microserviços com Java 21 e Spring Boot.

O projeto foi criado com o objetivo de aplicar conceitos de desenvolvimento backend moderno, segurança, APIs REST, persistência de dados, testes automatizados, containerização e computação em nuvem.

---

## 🚀 Tecnologias Utilizadas

### Backend

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven

### Frontend

* React
* JavaScript
* Thymeleaf
* HTML
* CSS

### DevOps e Cloud

* Docker
* Kubernetes
* AWS
* GitHub Actions (CI/CD)

### Testes

* JUnit 5
* Mockito

### Versionamento

* Git
* GitHub

---

# 🏗 Arquitetura do Projeto

O sistema foi dividido em dois microserviços independentes:

## Usuario Service

Responsável pelo gerenciamento de usuários e autenticação.

### Funcionalidades

* Cadastro de usuários
* Login
* Logout
* Recuperação de senha
* Redefinição de senha
* Criptografia de senhas com BCrypt
* Controle de acesso com Spring Security
* Backup de informações utilizando AWS

---

## Filmes Service

Responsável pelo gerenciamento do catálogo de filmes.

### Funcionalidades

* Cadastro de filmes
* Edição de filmes
* Exclusão de filmes
* Busca de filmes
* Listagem de filmes
* APIs REST

---

# 🔐 Segurança

A aplicação utiliza Spring Security para:

* Autenticação de usuários
* Proteção de rotas
* Criptografia de senhas utilizando BCrypt
* Controle de acesso

---

# 🗄 Banco de Dados

Banco utilizado:

* PostgreSQL

Principais entidades:

### Usuário

* Id
* Nome
* Email
* Senha

### Filme

* Id
* Título
* Gênero
* Ano
* Descrição

---

# 🐳 Docker

Os microserviços podem ser executados em containers Docker.

### Build

```bash
docker build -t usuario-service .
docker build -t filmes-service .
```

### Execução

```bash
docker run -p 8081:8081 usuario-service
docker run -p 8082:8082 filmes-service
```

---

# ☸ Kubernetes

O projeto possui arquivos Kubernetes para implantação dos serviços.

Arquivos disponíveis:

```text
K8s/
├── usuario-deployment.yaml
├── usuario-service.yaml
├── filmes-deployment.yaml
└── filmes-service.yaml
```

Esses arquivos permitem o gerenciamento e escalabilidade dos containers em ambientes Kubernetes.

---

# ☁ AWS

Integração com AWS para armazenamento e backup de informações da aplicação.

---

# 🧪 Testes

Foram implementados testes unitários utilizando:

* JUnit 5
* Mockito

Objetivos:

* Garantir qualidade do código
* Validar regras de negócio
* Facilitar futuras manutenções

---

# 🔄 Integração Contínua

O projeto utiliza GitHub Actions para execução automática do pipeline CI/CD.

Processos automatizados:

* Build da aplicação
* Execução dos testes
* Validação do projeto

---

# 📁 Estrutura do Projeto

```text
ScreenMatch-Microservices
│
├── usuario-service
│
├── filmes-service
│
├── screenmatch-react
│
├── K8s
│
└── .github/workflows
```

---

# ▶ Como Executar

### Clonar repositório

```bash
git clone https://github.com/Mateus-Flavio/ScreenMatch-Microservices.git
```

### Entrar na pasta

```bash
cd ScreenMatch-Microservices
```

### Executar os serviços

```bash
mvn spring-boot:run
```

---

# 📚 Objetivo do Projeto

Este projeto foi desenvolvido para consolidar conhecimentos em:

* Desenvolvimento Backend com Java
* Arquitetura de Microserviços
* Spring Boot
* Segurança de aplicações
* APIs REST
* Docker
* Kubernetes
* AWS
* React
* Testes Automatizados
* Integração Contínua (CI/CD)

---

# 👨‍💻 Autor

Mateus Flávio Herculano de Farias

GitHub:
https://github.com/Mateus-Flavio

LinkedIn:
(adicionar seu LinkedIn aqui)


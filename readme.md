# Projeto de Gerenciamento de Livraria

## Descrição
Este projeto consiste em uma aplicação backend desenvolvida em Java, utilizando o ecossistema Spring. O objetivo principal é oferecer funcionalidades de gerenciamento para uma livraria, contemplando entidades como `Livro`, `Autor`, `Editora`, `Etiqueta`, `Usuário` e `Empréstimo`. Foco total na administração, sem área de usuário.

## Tecnologias
- **Spring Boot** (v3.2.1)
- **Spring Data JPA**
- **Spring MVC**
- **Spring Validation**
- **Flyway Migration**
- **Lombok**
- **PostgreSQL**
- **SpringDoc Swagger** (v2.3.0)

## Endpoints
- `/v1/livros`: CRUD para gerenciar livros.
- `/v1/autores`: CRUD para gerenciar autores.
- `/v1/editoras`: CRUD para gerenciar editoras.
- `/v1/etiquetas`: CRUD para gerenciar etiquetas.
- `/v1/usuarios`: CRUD para gerenciar usuários.
- `/v1/emprestimos`: CRUD para gerenciar empréstimos.

## Documentação de API
Acesse a [documentação da API](http://localhost:8080/swagger-ui.html), após rodar o projeto localmente, porta definida 8080.

## Implementação Futura
- **Testes Unitários**: Incluir testes para garantir a robustez do código.
- **Spring Security**: Adicionar camadas de segurança para proteger os endpoints.

## Organização do Projeto
- Arquitetura em camadas. Esse tipo de arquitetura busca organizar o código de forma modular, separando as responsabilidades em diferentes camadas para facilitar a manutenção, escalabilidade e teste.

## Início Rápido
Projeto em andamento. Em breve, serão fornecidas instruções detalhadas para iniciar o aplicativo localmente e contribuir para o desenvolvimento.
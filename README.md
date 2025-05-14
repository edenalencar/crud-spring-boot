# 🚀 CRUD Spring Boot

## 📋 Descrição
Implementação de uma API RESTful completa usando Spring Boot para operações CRUD (Create, Read, Update, Delete).

## ✨ Características
- Arquitetura REST
- Autenticação e Autorização com Spring Security
- Tratamento de exceções personalizado
- Testes de integração e unitários
- Paginação e ordenação de resultados

## 🛠️ Tecnologias Utilizadas
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Maven
- JUnit 5

## 🚦 Endpoints
- `GET /v1/usuario/estudantes` - Lista todos os estudantes (paginado)
- `GET /v1/usuario/estudantes/{id}` - Obtém estudante por ID
- `GET /v1/usuario/estudantes/nome/{nome}` - Busca estudante por nome
- `POST /v1/admin/estudantes` - Cria novo estudante (requer ADMIN)
- `PUT /v1/admin/estudantes` - Atualiza estudante existente (requer ADMIN)
- `DELETE /v1/admin/estudantes/{id}` - Remove estudante (requer ADMIN)

## 🏃‍♂️ Como Executar
```bash
# Clone o repositório
git clone https://github.com/seu-usuario/crud-spring-boot.git

# Entre na pasta do projeto
cd crud-spring-boot

# Compile o projeto
./mvnw clean package

# Execute a aplicação
java -jar target/AprendendoSpringBoot.jar
```

## 🐳 Docker
```bash
# Construa a imagem
docker build -t crud-spring-boot .

# Execute o container
docker run -p 8080:8080 crud-spring-boot
```
## 📄 Licença
Este projeto está licenciado sob os termos da licença MIT.

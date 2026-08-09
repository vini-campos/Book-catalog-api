# Book Catalog API

API REST para gerenciamento de uma biblioteca, permitindo cadastrar livros, autores e clientes.

---

## Tecnologias

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- MySQL
- Lombok
- SpringDoc OpenAPI (Swagger)
- Maven

---

## Estrutura do projeto

```
src/
└── main/
    └── java/br/com/vini/library/
        ├── controllers/         # Endpoints
        ├── services/            # Regras de negócio
        ├── database/
        │   ├── models/          # Entidades JPA
        │   └── repositories/    # Interfaces do Spring Data
        ├── dtos/
        │   ├── requests/        # DTOs de entrada
        │   └── responses/       # DTOs de saída
        ├── enums/               # Enumerações
        └── exceptions/          # Exceções customizadas
```

---

## Como rodar localmente

### Pré-requisitos

- Java 21+
- Maven
- MySQL rodando localmente

### 1. Clone o repositório

```bash
git clone https://github.com/vini-campos/Book-catalog-api.git
cd Book-catalog-api
```

### 2. Configure o banco de dados

Crie um banco de dados MySQL:

```sql
CREATE DATABASE library;
```

### 3. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto (ou configure o `application.yaml`):

```env
DB_URL=jdbc:mysql://localhost:3306/library
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

### 4. Rode a aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8082`

### 5. Acesse a documentação

```
http://localhost:8082/swagger-ui/index.html
```

---
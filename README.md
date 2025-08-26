# CRUD API com Spring Boot e JPA

Este projeto é uma API RESTful desenvolvida com Spring Boot que implementa operações CRUD (Create, Read, Update, Delete) para gerenciar entidades no banco de dados.
Ele utiliza Spring Data JPA para persistência e mapeamento objeto-relacional.

## Funcionalidades

A API suporta os seguintes métodos HTTP:

- **GET**: Recuperar todos os registros ou um registro específico
- **POST**: Criar novos registros
- **PUT**: Atualizar um registro existente
- **DELETE**: Excluir registros por ID

### Endpoints Disponíveis

| Método | Endpoint | Descrição | Exemplo |
|--------|----------|-----------|---------|
| GET | `/api/users/getAll` | Listar todos os usuários | `curl http://localhost:8080/api/users/getAll` |
| GET | `/api/users/getById/{id}` | Buscar usuário por ID | `curl http://localhost:8080/api/users/getById/1` |
| POST | `/api/users/addUser` | Criar novo usuário | `curl -X POST -H "Content-Type: application/json" -d '{"nome":"João","password":123456}' http://localhost:8080/api/users/addUser` |
| PUT | `/api/users/updateUser` | Atualizar usuário existente | `curl -X PUT -H "Content-Type: application/json" -d '{"id":1,"nome":"João Silva","password":654321}' http://localhost:8080/api/users/updateUser` |
| DELETE | `/api/users/deleteUserById/{id}` | Excluir usuário por ID | `curl -X DELETE http://localhost:8080/api/users/deleteUserById/1` |

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.6**
- **Spring Data JPA**
- **H2 Database** (desenvolvimento e testes)
- **MySQL** (produção)
- **Maven** (gerenciamento de dependências)
- **JUnit 5** (testes)

## Configuração do Ambiente

### Desenvolvimento
Por padrão, a aplicação usa banco H2 em memória para facilitar o desenvolvimento local:
- Não requer instalação de banco de dados
- Console H2 disponível em: `http://localhost:8080/h2-console`
- Dados são resetados a cada reinicialização

### Produção
Para produção, configure as variáveis de ambiente:
```bash
export DATABASE_URL=jdbc:mysql://localhost:3306/seu_banco
export DATABASE_USERNAME=seu_usuario
export DATABASE_PASSWORD=sua_senha
java -jar target/JPAConnection-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

## Como Executar o Projeto

### Pré-requisitos
- Java 17 ou superior instalado
- Maven instalado
- IDE (como IntelliJ ou Eclipse) ou terminal

### Executando em Desenvolvimento

1. **Clone o repositório:**
   ```bash
   git clone <url-do-repositorio>
   cd CrudSpringBoot
   ```

2. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Acesse a aplicação:**
   - API: `http://localhost:8080/api/users`
   - Console H2: `http://localhost:8080/h2-console`

### Executando Testes

```bash
# Executar todos os testes
./mvnw test

# Executar apenas testes unitários
./mvnw test -Dtest="JpaConnectionApplicationTests"

# Executar apenas testes de integração
./mvnw test -Dtest="ControllerIntegrationTest"
```

### Construindo para Produção

```bash
# Compilar e gerar JAR
./mvnw clean package

# Executar o JAR gerado
java -jar target/JPAConnection-0.0.1-SNAPSHOT.jar
```

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── br/com/JPAproject/JPAConnection/
│   │       ├── JpaConnectionApplication.java     # Classe principal
│   │       ├── Endpoints/
│   │       │   └── Controller.java               # REST Controller
│   │       ├── entity/
│   │       │   └── Users.java                    # Entidade JPA
│   │       ├── repository/
│   │       │   └── RepositoryUser.java           # Repository JPA
│   │       └── service/
│   │           └── UsersService.java             # Camada de serviço
│   └── resources/
│       ├── application.properties                # Configuração de desenvolvimento
│       └── application-prod.properties           # Configuração de produção
└── test/
    ├── java/
    │   └── br/com/JPAproject/JPAConnection/
    │       ├── JpaConnectionApplicationTests.java
    │       └── Endpoints/
    │           └── ControllerIntegrationTest.java
    └── resources/
        └── application-test.properties           # Configuração de testes
```

## Melhorias Implementadas

- ✅ **Arquitetura em Camadas**: Separação clara entre Controller, Service e Repository
- ✅ **Tratamento de Erros**: Validações adequadas e códigos HTTP corretos
- ✅ **Configuração Flexível**: Diferentes perfis para desenvolvimento, teste e produção
- ✅ **Testes Abrangentes**: Testes unitários e de integração
- ✅ **Segurança**: Remoção de credenciais hardcoded, uso de variáveis de ambiente
- ✅ **Documentação**: README atualizado com exemplos e instruções claras

## Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

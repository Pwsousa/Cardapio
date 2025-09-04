# Microserviço de Cardápio

## Visão Geral
Microserviço responsável pelo gerenciamento do cardápio do restaurante Sabores Conectados.

## Funcionalidades
- ✅ CRUD completo de itens do cardápio
- ✅ Categorização de produtos (ENTRADA, PRATO_PRINCIPAL, SOBREMESA, BEBIDA, etc.)
- ✅ Controle de disponibilidade e status
- ✅ Busca por nome e filtros avançados
- ✅ Integração com Eureka Server
- ✅ API REST completa

## Tecnologias
- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Cloud 2023.0.3**
- **Spring Data JPA**
- **MySQL 8.0**
- **Flyway** (migrações)
- **ModelMapper**
- **Lombok**

## Estrutura do Projeto
```
cardapio/
├── Cardapio/                    # Projeto Spring Boot
│   ├── src/main/java/
│   │   └── br/com/saboresconectados/cardapio/
│   │       ├── model/           # Entidades JPA
│   │       ├── dto/             # Data Transfer Objects
│   │       ├── repository/      # Repositórios JPA
│   │       ├── service/         # Lógica de negócio
│   │       ├── controller/      # Controllers REST
│   │       └── config/          # Configurações
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   └── db/migration/        # Scripts Flyway
│   └── src/test/                # Testes unitários
├── Dockerfile                   # Container Docker
└── README.md                    # Este arquivo
```

## Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- MySQL 8.0+

### Configuração do Banco
```sql
CREATE DATABASE cardapio;
```

### Execução Local
```bash
cd cardapio/Cardapio
mvn spring-boot:run
```

### Execução com Docker
```bash
docker build -t cardapio-service .
docker run -p 8082:8082 cardapio-service
```

## Endpoints da API

### Base URL
- **Local**: http://localhost:8082/cardapio
- **Gateway**: http://localhost:8084/cardapio

### Principais Endpoints
- `GET /cardapio` - Listar com paginação
- `GET /cardapio/todos` - Listar todos
- `GET /cardapio/{id}` - Obter por ID
- `POST /cardapio` - Criar novo item
- `PUT /cardapio/{id}` - Atualizar item
- `DELETE /cardapio/{id}` - Excluir item
- `GET /cardapio/categoria/{categoria}` - Filtrar por categoria
- `GET /cardapio/disponiveis` - Listar disponíveis
- `GET /cardapio/buscar?nome={nome}` - Buscar por nome

### Exemplo de Uso
```bash
# Listar todos os itens
curl http://localhost:8084/cardapio/todos

# Criar novo item
curl -X POST http://localhost:8084/cardapio \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Hambúrguer Artesanal",
    "descricao": "Hambúrguer com carne artesanal",
    "preco": 29.90,
    "categoria": "PRATO_PRINCIPAL"
  }'
```

## Documentação Completa
Consulte o arquivo `API_CARDAPIO.md` para documentação detalhada da API.

## Configuração
O microserviço está configurado para:
- **Porta**: 8082
- **Banco**: MySQL (cardapio)
- **Eureka**: Registrado automaticamente
- **Gateway**: Acessível via http://localhost:8084/cardapio

## Desenvolvimento
Para contribuir com o desenvolvimento:
1. Clone o repositório
2. Configure o ambiente de desenvolvimento
3. Execute os testes: `mvn test`
4. Faça suas alterações
5. Execute os testes novamente
6. Faça commit das alterações

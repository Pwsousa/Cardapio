# API do Cardápio - Sabores Conectados

## Visão Geral
API REST para gerenciamento do cardápio do restaurante Sabores Conectados.

## Base URL
- **Local**: http://localhost:8082/cardapio
- **Gateway**: http://localhost:8084/cardapio

## Endpoints

### 1. Listar Itens do Cardápio

#### GET /cardapio
Lista todos os itens do cardápio com paginação.

**Parâmetros de Query:**
- `page` (opcional): Número da página (padrão: 0)
- `size` (opcional): Tamanho da página (padrão: 10)
- `sort` (opcional): Campo para ordenação

**Exemplo:**
```bash
GET /cardapio?page=0&size=5&sort=nome,asc
```

#### GET /cardapio/todos
Lista todos os itens do cardápio sem paginação.

**Exemplo:**
```bash
GET /cardapio/todos
```

### 2. Obter Item por ID

#### GET /cardapio/{id}
Retorna um item específico do cardápio.

**Parâmetros:**
- `id` (obrigatório): ID do item

**Exemplo:**
```bash
GET /cardapio/1
```

### 3. Criar Novo Item

#### POST /cardapio
Cria um novo item no cardápio.

**Body (JSON):**
```json
{
  "nome": "Pizza Margherita",
  "descricao": "Pizza com molho de tomate, mussarela e manjericão",
  "preco": 35.90,
  "categoria": "PRATO_PRINCIPAL",
  "imagemUrl": "https://exemplo.com/pizza.jpg"
}
```

**Categorias disponíveis:**
- ENTRADA
- PRATO_PRINCIPAL
- SOBREMESA
- BEBIDA
- LANCHE
- SALADA
- SOUP
- VEGETARIANO
- VEGANO

### 4. Atualizar Item

#### PUT /cardapio/{id}
Atualiza um item existente no cardápio.

**Parâmetros:**
- `id` (obrigatório): ID do item

**Body (JSON):**
```json
{
  "nome": "Pizza Margherita Atualizada",
  "descricao": "Pizza com molho de tomate, mussarela e manjericão fresco",
  "preco": 38.90,
  "categoria": "PRATO_PRINCIPAL",
  "status": "ATIVO",
  "disponivel": true,
  "imagemUrl": "https://exemplo.com/pizza-nova.jpg"
}
```

### 5. Excluir Item

#### DELETE /cardapio/{id}
Remove um item do cardápio.

**Parâmetros:**
- `id` (obrigatório): ID do item

**Exemplo:**
```bash
DELETE /cardapio/1
```

### 6. Filtrar por Categoria

#### GET /cardapio/categoria/{categoria}
Lista itens de uma categoria específica.

**Parâmetros:**
- `categoria` (obrigatório): Categoria do item

**Exemplo:**
```bash
GET /cardapio/categoria/PRATO_PRINCIPAL
```

### 7. Filtrar por Status

#### GET /cardapio/status/{status}
Lista itens com um status específico.

**Parâmetros:**
- `status` (obrigatório): Status do item

**Status disponíveis:**
- ATIVO
- INATIVO
- INDISPONIVEL

**Exemplo:**
```bash
GET /cardapio/status/ATIVO
```

### 8. Listar Itens Disponíveis

#### GET /cardapio/disponiveis
Lista apenas itens disponíveis para pedido.

**Exemplo:**
```bash
GET /cardapio/disponiveis
```

### 9. Filtrar por Categoria e Status

#### GET /cardapio/categoria/{categoria}/status/{status}
Lista itens de uma categoria específica com um status específico.

**Exemplo:**
```bash
GET /cardapio/categoria/PRATO_PRINCIPAL/status/ATIVO
```

### 10. Buscar por Nome

#### GET /cardapio/buscar?nome={nome}
Busca itens pelo nome (busca parcial).

**Parâmetros de Query:**
- `nome` (obrigatório): Nome ou parte do nome do item

**Exemplo:**
```bash
GET /cardapio/buscar?nome=hambúrguer
```

### 11. Filtrar por Faixa de Preço

#### GET /cardapio/preco?precoMin={min}&precoMax={max}
Lista itens dentro de uma faixa de preço.

**Parâmetros de Query:**
- `precoMin` (obrigatório): Preço mínimo
- `precoMax` (obrigatório): Preço máximo

**Exemplo:**
```bash
GET /cardapio/preco?precoMin=10.00&precoMax=30.00
```

### 12. Alterar Disponibilidade

#### PUT /cardapio/{id}/disponibilidade
Altera apenas a disponibilidade de um item.

**Parâmetros:**
- `id` (obrigatório): ID do item

**Body (JSON):**
```json
{
  "disponivel": false
}
```

### 13. Alterar Status

#### PUT /cardapio/{id}/status
Altera apenas o status de um item.

**Parâmetros:**
- `id` (obrigatório): ID do item

**Body (JSON):**
```json
{
  "status": "INATIVO"
}
```

### 14. Verificar Porta do Serviço

#### GET /cardapio/porta
Retorna a porta em que o serviço está executando.

**Exemplo:**
```bash
GET /cardapio/porta
```

## Códigos de Resposta HTTP

- `200 OK`: Requisição bem-sucedida
- `201 Created`: Item criado com sucesso
- `204 No Content`: Item excluído com sucesso
- `400 Bad Request`: Dados inválidos
- `404 Not Found`: Item não encontrado
- `500 Internal Server Error`: Erro interno do servidor

## Exemplos de Uso

### Criar um novo item
```bash
curl -X POST http://localhost:8084/cardapio \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Hambúrguer Artesanal",
    "descricao": "Hambúrguer com carne artesanal e ingredientes frescos",
    "preco": 29.90,
    "categoria": "PRATO_PRINCIPAL"
  }'
```

### Listar itens disponíveis
```bash
curl -X GET http://localhost:8084/cardapio/disponiveis
```

### Buscar por nome
```bash
curl -X GET "http://localhost:8084/cardapio/buscar?nome=pizza"
```

### Alterar disponibilidade
```bash
curl -X PUT http://localhost:8084/cardapio/1/disponibilidade \
  -H "Content-Type: application/json" \
  -d '{"disponivel": false}'
```

## Estrutura de Dados

### ItemCardapioDTO
```json
{
  "id": 1,
  "nome": "Hambúrguer Clássico",
  "descricao": "Hambúrguer de carne com alface, tomate e cebola",
  "preco": 25.90,
  "categoria": "PRATO_PRINCIPAL",
  "status": "ATIVO",
  "imagemUrl": "https://exemplo.com/hamburguer.jpg",
  "disponivel": true
}
```

### DisponibilidadeDTO
```json
{
  "disponivel": true
}
```

### StatusDTO
```json
{
  "status": "ATIVO"
}
```

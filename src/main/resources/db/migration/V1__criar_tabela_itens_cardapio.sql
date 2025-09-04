CREATE TABLE itens_cardapio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(500),
    preco DECIMAL(10,2) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    imagem_url VARCHAR(255),
    disponivel BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Inserir alguns dados de exemplo
INSERT INTO itens_cardapio (nome, descricao, preco, categoria, status, disponivel) VALUES
('Hambúrguer Clássico', 'Hambúrguer de carne com alface, tomate e cebola', 25.90, 'PRATO_PRINCIPAL', 'ATIVO', TRUE),
('Batata Frita', 'Porção de batata frita crocante', 12.50, 'ENTRADA', 'ATIVO', TRUE),
('Coca-Cola 350ml', 'Refrigerante gelado', 6.90, 'BEBIDA', 'ATIVO', TRUE),
('Pudim de Leite', 'Pudim caseiro com calda de caramelo', 8.90, 'SOBREMESA', 'ATIVO', TRUE),
('Salada Caesar', 'Salada fresca com molho caesar', 18.90, 'SALADA', 'ATIVO', TRUE),
('X-Bacon', 'Hambúrguer com bacon e queijo', 32.90, 'PRATO_PRINCIPAL', 'ATIVO', TRUE),
('Suco de Laranja', 'Suco natural de laranja', 9.90, 'BEBIDA', 'ATIVO', TRUE),
('Brownie com Sorvete', 'Brownie quente com sorvete de baunilha', 15.90, 'SOBREMESA', 'ATIVO', TRUE);

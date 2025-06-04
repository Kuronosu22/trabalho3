CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    preco NUMERIC(10, 2) NOT NULL CHECK (preco > 0),
    quantidade INTEGER NOT NULL CHECK (quantidade >= 0)
);
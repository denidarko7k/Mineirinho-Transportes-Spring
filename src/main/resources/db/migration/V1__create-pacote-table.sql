CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE pacote(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY
    destinatario VARCHAR(100) NOT NULL,
    remetente  VARCHAR(100) NOT NULL,
    cpf VARCHAR(12) NOT NULL,
    idrastreio INT NOT NULL
);
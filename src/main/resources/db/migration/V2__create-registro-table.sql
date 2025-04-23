CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE TABLE registro (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    locall VARCHAR(25) NOT NULL,
    dataa TIMESTAMP NOT NULL,
    pacote_id UUID,
    FOREIGN KEY(pacote_id) REFERENCES pacote(idrastreio) ON DELETE CASCADE
);
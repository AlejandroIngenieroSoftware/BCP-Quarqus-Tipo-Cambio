-- Índice para buscar rápido por dni y fecha
CREATE INDEX idx_exchange_queries_dni_date 
ON exchange_queries(dni, query_date);

-- Crear la tabla
CREATE TABLE exchange_queries (
    id BIGSERIAL PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    getDate DATE NOT NULL DEFAULT CURRENT_DATE
);


-- Crear índice compuesto para búsquedas eficientes
CREATE INDEX idx_exchange_queries_dni_fecha 
ON exchange_queries(dni, getDate);

-- Contar consultas hoy para ese DNI
SELECT COUNT(*) 
FROM exchange_queries 
WHERE dni = '12345678' 
  AND getDate = CURRENT_DATE;

select * from exchange_queries;
delete from exchange_queries;
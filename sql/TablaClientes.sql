DROP TABLE IF EXISTS clientes;
CREATE TABLE IF NOT EXISTS clientes (
    id INT PRIMARY KEY,
    customer_id VARCHAR(20),
    nombre VARCHAR(100) not null,
    apellido VARCHAR(100),
    empresa VARCHAR(150),
    ciudad VARCHAR(100),
    pais VARCHAR(100),
    telefono1 VARCHAR(50),
    telefono2 VARCHAR(50),
    email VARCHAR(150),
    fecha_suscripcion DATE,
    web VARCHAR(200),
    activo BOOLEAN NOT NULL DEFAULT TRUE
);
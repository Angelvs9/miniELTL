--Temporal de clientes

DROP TABLE IF EXISTS TMP_CLIENTES_NO_INSERTADOS;
CREATE TABLE IF NOT EXISTS TMP_CLIENTES_NO_INSERTADOS (
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
    nlinea INT,
    motivo VARCHAR(500),
    fecha_carga TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    );
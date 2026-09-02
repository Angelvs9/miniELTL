--sql de la tabla de facturas
drop table if exists facturas;
CREATE TABLE IF NOT EXISTS facturas (
    ruc VARCHAR(15) PRIMARY KEY NOT NULL,
    id_cliente INT NOT NULL,
    tipo_registro VARCHAR(30),
    tipo_comprobante VARCHAR(30),
    fecha_emision varchar(7),
    metodo_pago VARCHAR(30),
    numero_comprobante VARCHAR(20),
    iva_porcentaje DECIMAL(5,2),
    parte_iva DECIMAL(12,2),
    total DECIMAL(12,2),
    imputa_iva VARCHAR(2),
    imputa_ire VARCHAR(2),
    imputa_irp VARCHAR(2),
    imputar VARCHAR(2)
);
--CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES clientes(id)
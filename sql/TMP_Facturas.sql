--sql de la tabla temporal de facturas
--sql de la tabla temporal de facturas
drop table if exists TMP_FACTURAS_NO_INSERTADAS;
Create table IF not exists TMP_FACTURAS_NO_INSERTADAS (
    nlinea INT,
    motivo VARCHAR(500),
    fecha_carga TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ruc VARCHAR(400),
    id_cliente VARCHAR(400),
    tipo_registro VARCHAR(400),
    tipo_comprobante VARCHAR(400),
    fecha_emision VARCHAR(400),
    metodo_pago VARCHAR(400),
    numero_comprobante  VARCHAR(400),
    iva_porcentaje VARCHAR(400),
    parte_iva VARCHAR(400),
    linea_original VARCHAR(400)
    total VARCHAR(400),
    imputa_iva VARCHAR(400),
    imputa_ire VARCHAR(400),
    imputa_irp VARCHAR(400),
    imputar VARCHAR(400),
);
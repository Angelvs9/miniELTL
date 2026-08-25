package MODELO;

import java.sql.Date;

public class Factura {

    private String ruc;
    private int idCliente;
    private String tipoRegistro;
    private String tipoComprobante;
    private String fechaEmision;
    private String metodoPago;
    private int ncomprobante;
    private double ivaPorcentaje;
    private double parteIva;
    private double total;
    private String imputaIVA;
    private String imputaIRE;
    private String imputaIRP;
    private String imputar;

    public Factura(String ruc, int idCliente, String tipoRegistro, String tipoComprobante, String fechaEmision, String metodoPago, int ncomprobante, double ivaPorcentaje, double parteIva, double total, String imputaIVA, String imputaIRE, String imputaIRP, String imputar) {
        this.ruc = ruc;
        this.idCliente = idCliente;
        this.tipoRegistro = tipoRegistro;
        this.tipoComprobante = tipoComprobante;
        this.fechaEmision = fechaEmision;
        this.metodoPago = metodoPago;
        this.ncomprobante = ncomprobante;
        this.ivaPorcentaje = ivaPorcentaje;
        this.parteIva = parteIva;
        this.total = total;
        this.imputaIVA = imputaIVA;
        this.imputaIRE = imputaIRE;
        this.imputaIRP = imputaIRP;
        this.imputar = imputar;
    }

    public Factura(String ruc, String tipoRegistro, String tipoComprobante, String fechaEmision, String metodoPago, int ncomprobante, double ivaPorcentaje, double parteIva, double total, String imputaIVA, String imputaIRE, String imputaIRP, String imputar) {
        this.ruc = ruc;
        this.tipoRegistro = tipoRegistro;
        this.tipoComprobante = tipoComprobante;
        this.fechaEmision = fechaEmision;
        this.metodoPago = metodoPago;
        this.ncomprobante = ncomprobante;
        this.ivaPorcentaje = ivaPorcentaje;
        this.parteIva = parteIva;
        this.total = total;
        this.imputaIVA = imputaIVA;
        this.imputaIRE = imputaIRE;
        this.imputaIRP = imputaIRP;
        this.imputar = imputar;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getNcomprobante() {
        return ncomprobante;
    }

    public void setNcomprobante(int ncomprobante) {
        this.ncomprobante = ncomprobante;
    }

    public double getIvaPorcentaje() {
        return ivaPorcentaje;
    }

    public void setIvaPorcentaje(double ivaPorcentaje) {
        this.ivaPorcentaje = ivaPorcentaje;
    }

    public double getParteIva() {
        return parteIva;
    }

    public void setParteIva(double parteIva) {
        this.parteIva = parteIva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getImputaIVA() {
        return imputaIVA;
    }

    public void setImputaIVA(String imputaIVA) {
        this.imputaIVA = imputaIVA;
    }

    public String getImputaIRE() {
        return imputaIRE;
    }

    public void setImputaIRE(String imputaIRE) {
        this.imputaIRE = imputaIRE;
    }

    public String getImputaIRP() {
        return imputaIRP;
    }

    public void setImputaIRP(String imputaIRP) {
        this.imputaIRP = imputaIRP;
    }

    public String getImputar() {
        return imputar;
    }

    public void setImputar(String imputar) {
        this.imputar = imputar;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "ruc='" + ruc + '\'' +
                ", idCliente=" + idCliente +
                ", tipoRegistro='" + tipoRegistro + '\'' +
                ", tipoComprobante='" + tipoComprobante + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", metodoPago='" + metodoPago + '\'' +
                ", ncomprobante=" + ncomprobante +
                ", ivaPorcentaje=" + ivaPorcentaje +
                ", parteIva=" + parteIva +
                ", total=" + total +
                ", imputaIVA='" + imputaIVA + '\'' +
                ", imputaIRE='" + imputaIRE + '\'' +
                ", imputaIRP='" + imputaIRP + '\'' +
                ", imputar='" + imputar + '\'' +
                '}';
    }
}



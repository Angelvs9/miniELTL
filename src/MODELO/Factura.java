package MODELO;

import java.sql.Date;

public class Factura {

    private String Ruc;
    private String tipoRegistro;
    private String tipoComprobante;
    private Date fechaEmision;
    private String MetodoPago;
    private int Ncomprobante;
    private double ivaPorcentaje;
    private double parteIva;
    private double total;
    private String ImputaIVA;
    private String ImputaIRE;
    private String ImputaIRP;
    private String Imputar;


    public Factura(String ruc, String tipoRegistro, String tipoComprobante, Date fechaEmision, String metodoPago, int ncomprobante, double ivaPorcentaje, double parteIva, double total, String imputaIVA, String imputaIRE, String imputaIRP, String imputar) {
        Ruc = ruc;
        this.tipoRegistro = tipoRegistro;
        this.tipoComprobante = tipoComprobante;
        this.fechaEmision = fechaEmision;
        MetodoPago = metodoPago;
        Ncomprobante = ncomprobante;
        this.ivaPorcentaje = ivaPorcentaje;
        this.parteIva = parteIva;
        this.total = total;
        ImputaIVA = imputaIVA;
        ImputaIRE = imputaIRE;
        ImputaIRP = imputaIRP;
        Imputar = imputar;
    }

    public String getRuc() {
        return Ruc;
    }

    public void setRuc(String ruc) {
        Ruc = ruc;
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

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getMetodoPago() {
        return MetodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        MetodoPago = metodoPago;
    }

    public int getNcomprobante() {
        return Ncomprobante;
    }

    public void setNcomprobante(int ncomprobante) {
        Ncomprobante = ncomprobante;
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
        return ImputaIVA;
    }

    public void setImputaIVA(String imputaIVA) {
        ImputaIVA = imputaIVA;
    }

    public String getImputaIRE() {
        return ImputaIRE;
    }

    public void setImputaIRE(String imputaIRE) {
        ImputaIRE = imputaIRE;
    }

    public String getImputaIRP() {
        return ImputaIRP;
    }

    public void setImputaIRP(String imputaIRP) {
        ImputaIRP = imputaIRP;
    }

    public String getImputar() {
        return Imputar;
    }

    public void setImputar(String imputar) {
        Imputar = imputar;
    }


    @Override
    public String toString() {
        return "Factura{" +
                "Ruc='" + Ruc + '\'' +
                ", tipoRegistro='" + tipoRegistro + '\'' +
                ", tipoComprobante='" + tipoComprobante + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", MetodoPago='" + MetodoPago + '\'' +
                ", Ncomprobante=" + Ncomprobante +
                ", ivaPorcentaje=" + ivaPorcentaje +
                ", parteIva=" + parteIva +
                ", total=" + total +
                ", ImputaIVA='" + ImputaIVA + '\'' +
                ", ImputaIRE='" + ImputaIRE + '\'' +
                ", ImputaIRP='" + ImputaIRP + '\'' +
                ", Imputar='" + Imputar + '\'' +
                '}';
    }
}




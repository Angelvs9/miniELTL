package MODELO;

import org.h2.index.Index;

import java.util.Date;

public class Cliente {
    private int id;
    private String cliente_id;
    private String nombre;
    private String apellido;
    private String empresa;
    private String ciudad;
    private String pais;
    private int ntelefono;
    private int ntelefono2;
    private String email;
    private String suscripcion;
    private Date fecha;
    private String web;

    public Cliente(int id, String cliente_id, String nombre, String apellido, String empresa, String ciudad, String pais, int ntelefono, int ntelefono2, String email, String suscripcion, Date fecha, String web) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.empresa = empresa;
        this.ciudad = ciudad;
        this.pais = pais;
        this.ntelefono = ntelefono;
        this.ntelefono2 = ntelefono2;
        this.email = email;
        this.suscripcion = suscripcion;
        this.fecha = fecha;
        this.web = web;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getNtelefono() {
        return ntelefono;
    }

    public void setNtelefono(int ntelefono) {
        this.ntelefono = ntelefono;
    }

    public int getNtelefono2() {
        return ntelefono2;
    }

    public void setNtelefono2(int ntelefono2) {
        this.ntelefono2 = ntelefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(String suscripcion) {
        this.suscripcion = suscripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", cliente_id='" + cliente_id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", empresa='" + empresa + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                ", ntelefono=" + ntelefono +
                ", ntelefono2=" + ntelefono2 +
                ", email='" + email + '\'' +
                ", suscripcion='" + suscripcion + '\'' +
                ", fecha=" + fecha +
                ", web='" + web + '\'' +
                '}';
    }
}

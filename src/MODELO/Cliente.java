package MODELO;

import java.sql.Date;

public class Cliente {
    private int id;
    private String cliente_id;
    private String nombre;
    private String apellido;
    private String empresa;
    private String ciudad;
    private String pais;
    private String ntelefono;
    private String ntelefono2;
    private String email;
    private Date suscripcion;
    private String web;
    private boolean activo;

    public Cliente(int id, String cliente_id, String nombre, String apellido, String empresa, String ciudad, String pais, String ntelefono, String ntelefono2, String email, Date suscripcion, String web) {
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
        this.web = web;
        this.activo=true;
    }

    public Cliente(int id, String cliente_id, String nombre, String apellido, String empresa, String ciudad, String pais, String ntelefono, String ntelefono2, String email, Date suscripcion, String web, boolean activo) {
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
        this.web = web;
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    public String getNtelefono() {
        return ntelefono;
    }

    public void setNtelefono(String ntelefono) {
        this.ntelefono = ntelefono;
    }

    public String getNtelefono2() {
        return ntelefono2;
    }

    public void setNtelefono2(String ntelefono2) {
        this.ntelefono2 = ntelefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public java.sql.Date getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Date suscripcion) {
        this.suscripcion = suscripcion;
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
                ", ntelefono='" + ntelefono + '\'' +
                ", ntelefono2='" + ntelefono2 + '\'' +
                ", email='" + email + '\'' +
                ", suscripcion=" + suscripcion +
                ", web='" + web + '\'' +
                '}';
    }
}

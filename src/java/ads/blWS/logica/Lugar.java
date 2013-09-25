/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ads.blWS.logica;

/**
 *
 * @author Julião
 */
public class Lugar {
    
    private String nombre;
    private String direccion;
    private String clasificacion;
    private String icono;
    private String referencia;
    private String id;
    private String vecindario;
    private String numeroTelefonoF;
    private String numeroTelefonoI;
    private String url;
    private String web;

    public Lugar() {
    }

    public Lugar(String nombre, String direccion, String clasificacion, String icono, String referencia, String id) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.clasificacion = clasificacion;
        this.icono = icono;
        this.referencia = referencia;
        this.id = id;
    }
    
    public Lugar(String nombre, String direccion, String clasificacion, String icono, String referencia, String id, String vecindario, String numeroTelefonoF, String numeroTelefonoI, String url, String web) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.clasificacion = clasificacion;
        this.icono = icono;
        this.referencia = referencia;
        this.id = id;
        this.vecindario = vecindario;
        this.numeroTelefonoF = numeroTelefonoF;
        this.numeroTelefonoI = numeroTelefonoI;
        this.url = url;
        this.web = web;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVecindario() {
        return vecindario;
    }

    public void setVecindario(String vecindario) {
        this.vecindario = vecindario;
    }

    public String getNumeroTelefonoF() {
        return numeroTelefonoF;
    }

    public void setNumeroTelefonoF(String numeroTelefonoF) {
        this.numeroTelefonoF = numeroTelefonoF;
    }

    public String getNumeroTelefonoI() {
        return numeroTelefonoI;
    }

    public void setNumeroTelefonoI(String numeroTelefonoI) {
        this.numeroTelefonoI = numeroTelefonoI;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    
}

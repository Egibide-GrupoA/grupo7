/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package himevico;

import java.util.Date;

/**
 * Clase incidencia
 *
 * @author Sheila y Asier
 */
public class Incidencia {

    private int idIncidencia;
    private String mensaje;
    private Date fecha;
    private Date resuelta;

    public Incidencia() {
    }

    /**
     * Constructor que genera un objeto tipo incidencia
     *
     * @param idIncidencia
     * @param mensaje
     * @param fecha
     * @param resuelta
     */
    public Incidencia(int idIncidencia, String mensaje, Date fecha, Date resuelta) {
        this.idIncidencia = idIncidencia;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.resuelta = resuelta;
    }

    /**
     * @return the idIncidencia
     */
    public int getIdIncidencia() {
        return idIncidencia;
    }

    /**
     * @param idIncidencia the idIncidencia to set
     */
    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the resuelta
     */
    public Date getResuelta() {
        return resuelta;
    }

    /**
     * @param resuelta the resuelta to set
     */
    public void setResuelta(Date resuelta) {
        this.resuelta = resuelta;
    }

}

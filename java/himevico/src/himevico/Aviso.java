/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package himevico;

/**
 * Clase aviso
 *
 * @author Sheila y Asier
 */
public class Aviso {

    private int idAviso;
    private String mensaje;
    private boolean visto;
    private int idParte;

    /**
     * Crear aviso vacío
     */
    public Aviso() {
    }

    /**
     * Constructor que genera un objeto tipo aviso
     *
     * @param idAviso
     * @param mensaje
     * @param visto
     */
    public Aviso(int idAviso, String mensaje, boolean visto) {
        this.idAviso = idAviso;
        this.mensaje = mensaje;
        this.visto = visto;
    }
    
    /**
     * Crear objeto de Aviso
     * @param idAviso
     * @param mensaje
     * @param visto
     * @param idParte
     */
    public Aviso(int idAviso, String mensaje, boolean visto, int idParte) {
        this.idAviso = idAviso;
        this.mensaje = mensaje;
        this.visto = visto;
        this.idParte = idParte;

    }
    /**
     * @return the idAviso
     */
    public int getIdAviso() {
        return idAviso;
    }

    /**
     * @param idAviso the idAviso to set
     */
    public void setIdAviso(int idAviso) {
        this.idAviso = idAviso;
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
     * @return the visto
     */
    public boolean isVisto() {
        return visto;
    }

    /**
     * @param visto the visto to set
     */
    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    /**
     * @return int
     */
    public int getIdParte() {
        return idParte;
    }

    /**
     * @param idParte
     */
    public void setIdParte(int idParte) {
        this.idParte = idParte;
    }

}

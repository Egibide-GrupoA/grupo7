/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package himevico;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sheila
 */
public class Centro {

    
    private int idCentro;
    private String nombre;
    private String calle;
    private int numero;
    private int piso;
    private char mano;
    private int codPostal;
    private String ciudad;
    private String provincia;
    private String telefono;

    public Centro(int idCentro, GestorBBDD db) throws Exception {
        ResultSet rs;
        rs = db.selectCentro(idCentro);
        if (rs.next()) {
            this.idCentro = rs.getInt("id");
            this.nombre = rs.getString("nombre");
            this.calle = rs.getString("calle");
            this.numero = rs.getInt("numero");
            this.piso = rs.getInt("piso");
            this.mano = rs.getString("mano").charAt(0);
            this.codPostal = rs.getInt("codPostal");
            this.ciudad = rs.getString("ciudad");
            this.provincia = rs.getString("provincia");
            this.telefono = rs.getString("telefono");
        }
    
    }

    public Centro(int idCentro, String nombre, String calle, int numero, int codPostal, String ciudad, String provincia, String telefono) {
        this.idCentro = idCentro;
        this.nombre = nombre;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.mano = mano;
        this.codPostal = codPostal;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.telefono = telefono;
        GestorBBDD.crearCentro(nombre, calle, numero, piso, mano, codPostal, ciudad, provincia, telefono);
    }

    /**
     * @return the idCentro
     */
    public int getIdCentro() {
        return idCentro;
    }

    /**
     * @param idCentro the idCentro to set
     */
    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the codPostal
     */
    public int getCodPostal() {
        return codPostal;
    }

    /**
     * @param codPostal the codPostal to set
     */
    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }
    
    /**
     * @return the piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }

  
       /**
     * @return the mano
     */
    public char getMano() {
        return mano;
    }

    /**
     * @param mano the mano to set
     */
    public void setMano(char mano) {
        this.mano = mano;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    
    
    
}
